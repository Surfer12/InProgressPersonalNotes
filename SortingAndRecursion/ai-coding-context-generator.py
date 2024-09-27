import os
import re
from typing import List, Dict

class AICodingContextGenerator:
    def __init__(self, project_root: str):
        self.project_root = project_root
        self.project_files = self._get_project_files()
        self.conversation_files = set()
        self.file_contents = {}

    def _get_project_files(self) -> List[str]:
        project_files = []
        for root, _, files in os.walk(self.project_root):
            for file in files:
                if file.endswith(('.py', '.js', '.html', '.css', '.json')):
                    project_files.append(os.path.join(root, file))
        return project_files

    def add_conversation_file(self, file_path: str):
        if os.path.exists(file_path):
            self.conversation_files.add(file_path)
        else:
            print(f"Warning: File {file_path} does not exist in the project.")

    def _read_file_content(self, file_path: str) -> str:
        if file_path not in self.file_contents:
            with open(file_path, 'r') as file:
                self.file_contents[file_path] = file.read()
        return self.file_contents[file_path]

    def _extract_functions(self, content: str) -> List[str]:
        function_pattern = r'def\s+(\w+)\s*\(.*?\):'
        return re.findall(function_pattern, content)

    def _extract_classes(self, content: str) -> List[str]:
        class_pattern = r'class\s+(\w+)(?:\(.*?\))?:'
        return re.findall(class_pattern, content)

    def generate_context(self) -> Dict[str, Dict]:
        context = {}

        for file_path in set(self.project_files) | self.conversation_files:
            file_content = self._read_file_content(file_path)
            functions = self._extract_functions(file_content)
            classes = self._extract_classes(file_content)

            context[file_path] = {
                'functions': functions,
                'classes': classes,
                'is_conversation_file': file_path in self.conversation_files
            }

        return context

    def provide_insights(self) -> str:
        context = self.generate_context()
        insights = []

        total_files = len(context)
        total_functions = sum(len(v['functions']) for v in context.values())
        total_classes = sum(len(v['classes']) for v in context.values())

        insights.append(f"Project Overview:")
        insights.append(f"- Total files analyzed: {total_files}")
        insights.append(f"- Total functions found: {total_functions}")
        insights.append(f"- Total classes found: {total_classes}")

        if self.conversation_files:
            insights.append("\nFiles mentioned in conversation:")
            for file in self.conversation_files:
                if file in context:
                    insights.append(f"- {file}")
                    insights.append(f"  Functions: {', '.join(context[file]['functions'])}")
                    insights.append(f"  Classes: {', '.join(context[file]['classes'])}")

        return "\n".join(insights)

# Example usage
if __name__ == "__main__":
    generator = AICodingContextGenerator("/path/to/project/root")
    generator.add_conversation_file("/path/to/project/root/specific_file.py")
    print(generator.provide_insights())
