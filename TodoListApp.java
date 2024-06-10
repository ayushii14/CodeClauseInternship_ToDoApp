import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TodoListApp extends JFrame {

    private DefaultListModel<String> tasksListModel;
    private JList<String> tasksList;

    public TodoListApp() {
        setTitle("Todo List App");
        setSize(550, 400);
        setLocation(30,30);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        tasksListModel = new DefaultListModel<>();
        tasksList = new JList<>(tasksListModel);

        JScrollPane scrollPane = new JScrollPane(tasksList);

        JTextField taskInput = new JTextField();
        taskInput.setPreferredSize(new Dimension(250, 25));

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String task = taskInput.getText().trim();
                if (!task.isEmpty()) {
                    tasksListModel.addElement(task);
                    taskInput.setText("");
                }
            }
        });

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = tasksList.getSelectedIndex();
                if (selectedIndex != -1) {
                    tasksListModel.remove(selectedIndex);
                }
            }
        });

        JButton completeButton = new JButton("Complete");
        completeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = tasksList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String task = tasksListModel.getElementAt(selectedIndex);
                    tasksListModel.setElementAt("[Completed] " + task, selectedIndex);
                }
            }
        });

        JPanel inputPanel = new JPanel();
        inputPanel.add(taskInput);
        inputPanel.add(addButton);
        inputPanel.add(deleteButton);
        inputPanel.add(completeButton);

        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(inputPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TodoListApp();
            }
        });
    }
}