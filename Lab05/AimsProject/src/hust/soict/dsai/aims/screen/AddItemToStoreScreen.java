package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.store.Store;

import javax.swing.*;
import java.awt.*;

public abstract class AddItemToStoreScreen extends JFrame {
    protected final Store store;
    protected final Cart cart;
    protected final JTextField tfId = new JTextField(20);
    protected final JTextField tfTitle = new JTextField(20);
    protected final JTextField tfCategory = new JTextField(20);
    protected final JTextField tfCost = new JTextField(20);

    private final JPanel formPanel = new JPanel(new GridBagLayout());
    private int currentRow = 0;

    protected AddItemToStoreScreen(Store store, Cart cart, String title) {
        this.store = store;
        this.cart = cart;

        setTitle(title);
        setLayout(new BorderLayout(10, 10));
        add(createHeader(title), BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);

        addRow("Id", tfId);
        addRow("Title", tfTitle);
        addRow("Category", tfCategory);
        addRow("Cost", tfCost);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    protected void addRow(String label, JComponent component) {
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.gridx = 0;
        labelConstraints.gridy = currentRow;
        labelConstraints.insets = new Insets(6, 12, 6, 6);
        labelConstraints.anchor = GridBagConstraints.LINE_END;
        formPanel.add(new JLabel(label + ":"), labelConstraints);

        GridBagConstraints fieldConstraints = new GridBagConstraints();
        fieldConstraints.gridx = 1;
        fieldConstraints.gridy = currentRow;
        fieldConstraints.insets = new Insets(6, 6, 6, 12);
        fieldConstraints.fill = GridBagConstraints.HORIZONTAL;
        fieldConstraints.weightx = 1;
        formPanel.add(component, fieldConstraints);

        currentRow++;
    }

    protected void showScreen() {
        add(createButtons(), BorderLayout.SOUTH);
        pack();
        setMinimumSize(new Dimension(420, getHeight()));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    protected String readRequired(JTextField field, String fieldName) {
        String value = field.getText().trim();
        if (value.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " is required.");
        }
        return value;
    }

    protected String readOptional(JTextField field) {
        return field.getText().trim();
    }

    protected int readInt(JTextField field, String fieldName) {
        try {
            return Integer.parseInt(readRequired(field, fieldName));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " must be an integer.");
        }
    }

    protected float readFloat(JTextField field, String fieldName) {
        try {
            return Float.parseFloat(readRequired(field, fieldName));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fieldName + " must be a number.");
        }
    }

    protected abstract Media createMedia();

    private JPanel createHeader(String title) {
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel(title);
        label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 32));
        label.setForeground(Color.CYAN);
        header.add(label);
        return header;
    }

    private JPanel createButtons() {
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            new StoreScreen(store, cart);
            dispose();
        });

        JButton addButton = new JButton("Add to store");
        addButton.addActionListener(e -> addMediaToStore());

        buttons.add(cancelButton);
        buttons.add(addButton);
        return buttons;
    }

    private void addMediaToStore() {
        try {
            Media media = createMedia();
            store.addMedia(media);
            JOptionPane.showMessageDialog(this, media.getTitle() + " added to store.");
            new StoreScreen(store, cart);
            dispose();
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Invalid input", JOptionPane.ERROR_MESSAGE);
        }
    }
}
