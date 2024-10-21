package MementoDesignPattern;

public class TextEditor {
    private String content;

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    // Create a memento (save state)
    public TextEditorMemento save() {
        return new TextEditorMemento(content);
    }

    // Restore state from memento
    public void restore(TextEditorMemento memento) {
        this.content = memento.getContent();
    }
}