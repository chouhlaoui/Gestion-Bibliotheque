
// Juste pour afficher un hint
class hint extends javax.swing.plaf.basic.BasicTextFieldUI implements java.awt.event.FocusListener {
    private String hint;
    private boolean hideOnFocus;
    private java.awt.Color color;

    public hint(String hint, boolean hideOnFocus) {
        this.hint = hint;
        this.hideOnFocus = hideOnFocus;
        this.color = java.awt.Color.gray;
    }

    private void repaint() {
        if (getComponent() != null) {
            getComponent().repaint();
        }
    }

    @Override
    protected void paintSafely(java.awt.Graphics g) {
        super.paintSafely(g);
        javax.swing.JTextField component = (javax.swing.JTextField) getComponent();
        if (component.getText().length() == 0 && !component.hasFocus()) {
            g.setColor(color);
            int padding = (component.getHeight() - component.getFont().getSize()) / 2;
            int inset = 3;
            g.drawString(hint, inset, component.getHeight() - padding - inset);
        }
    }

    @Override
    public void focusGained(java.awt.event.FocusEvent e) {
        if (hideOnFocus) {
            repaint();
        }
    }

    @Override
    public void focusLost(java.awt.event.FocusEvent e) {
        if (hideOnFocus) {
            repaint();
        }
    }

    @Override
    public void installListeners() {
        super.installListeners();
        getComponent().addFocusListener(this);
    }

    @Override
    public void uninstallListeners() {
        super.uninstallListeners();
        getComponent().removeFocusListener(this);
    }
}