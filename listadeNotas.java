package Calculadora;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class listadeNotas extends JFrame {

    private List<String> notes;
    private JTextArea textArea;
    private JList<String> notesList;

    public listadeNotas() {
        notes = new ArrayList<>();
        setupUI();
    }

    private void setupUI() {
        setTitle("Notepad");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        JButton createButton = new JButton("Nueva Nota");
        JButton viewButton = new JButton("Ver Nota");
        JButton deleteButton = new JButton("Eliminar Nota");

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String note = textArea.getText();
                notes.add(note);
                textArea.setText("");
                updateNotesList();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = notesList.getSelectedIndex();
                if (selectedIndex >= 0 && selectedIndex < notes.size()) {
                    String note = notes.get(selectedIndex);
                    JOptionPane.showMessageDialog(null, note, "Nota", JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una nota de la lista.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = notesList.getSelectedIndex();
                if (selectedIndex >= 0 && selectedIndex < notes.size()) {
                    notes.remove(selectedIndex);
                    updateNotesList();
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una nota de la lista.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.add(createButton);
        buttonsPanel.add(viewButton);
        buttonsPanel.add(deleteButton);

        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);

        notesList = new JList<>();
        add(new JScrollPane(notesList), BorderLayout.WEST);

        setVisible(true);
    }

    private void updateNotesList() {
        String[] notesArray = notes.toArray(new String[0]);
        notesList.setListData(notesArray);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new listadeNotas();
            }
        });
    }
}