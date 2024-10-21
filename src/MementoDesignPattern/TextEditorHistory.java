package MementoDesignPattern;

import java.util.Stack;

public class TextEditorHistory {
    private Stack<TextEditorMemento> history = new Stack<>();

    public void save(TextEditor textEditor) {
        history.push(textEditor.save());
    }

    public void undo(TextEditor textEditor) {
        if (!history.isEmpty()) {
            textEditor.restore(history.pop());
        }
    }
}
