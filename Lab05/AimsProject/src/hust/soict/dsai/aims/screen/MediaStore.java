package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;

import javax.swing.*;
import java.awt.*;

public class MediaStore extends JPanel {
    private Media media;
    private Cart cart;

    public MediaStore(Media media, Cart cart) {
        this.media = media;
        this.cart = cart;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(media.getTitle());
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 20));
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel cost = new JLabel(media.getCost() + " $");
        cost.setAlignmentX(CENTER_ALIGNMENT);

        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton addToCartButton = new JButton("Add to cart");
        addToCartButton.addActionListener(e -> {
            try {
                cart.addMedia(media);
                JOptionPane.showMessageDialog(this, media.getTitle() + " added to cart!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        container.add(addToCartButton);

        if (media instanceof Playable) {
            JButton playButton = new JButton("Play");
            playButton.addActionListener(e -> {
                try {
                    ((Playable) media).play();
                    JOptionPane.showMessageDialog(this, "Playing: " + media.getTitle());
                } catch (PlayerException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Play error", JOptionPane.ERROR_MESSAGE);
                }
            });
            container.add(playButton);
        }

        add(Box.createVerticalGlue());
        add(title);
        add(cost);
        add(Box.createVerticalGlue());
        add(container);
        add(Box.createVerticalGlue());

        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}