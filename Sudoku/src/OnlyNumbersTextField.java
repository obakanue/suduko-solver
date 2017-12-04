import javafx.scene.control.TextField;

public class OnlyNumbersTextField extends TextField {

	/*
	 * Creates an OnlyNumbersTextField object, anything other than 1-9 gives
	 * text value 0.
	 */
	public OnlyNumbersTextField(String text) {
		super("");
		if (matches(text)) {
			insertString(0, text);
		}
	}

	private void insertString(int index, String text) {
		super.insertText(index, text);
	}

	@Override
	public void replaceText(int start, int end, String text) {
		if (matches(text)) {
			super.replaceText(start, end, text);
		}
	}

	@Override
	public void replaceSelection(String text) {
		if (matches(text)) {
			super.replaceSelection(text);
		}
	}

	private boolean matches(String text) {
		return text.isEmpty() || (getText().length() < 1) && text.matches("[1-9]");
	}

}
