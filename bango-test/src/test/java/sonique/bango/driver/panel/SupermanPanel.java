package sonique.bango.driver.panel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import sonique.bango.driver.component.SupermanButton;
import sonique.bango.driver.component.SupermanFields;
import sonique.bango.driver.component.SupermanTextField;

public abstract class SupermanPanel {
    private final WebElement rootElement;
    private final SupermanFields fields;

    public SupermanPanel(WebElement rootElement) {
        this.rootElement = rootElement;
        fields = new SupermanFields(rootElement);
    }

    protected SupermanButton button(By by) {
        return new SupermanButton(rootElement.findElement(by));
    }

    protected SupermanTextField textField(String label) {
        return fields.textField(label);
    }
}