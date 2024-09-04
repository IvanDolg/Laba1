package application;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class DogBreedSearchApp extends Frame implements ActionListener {
    private Button bex = new Button("Exit");
    private Button sea = new Button("Search");
    private TextArea resultArea = new TextArea();
    private TextField urlField1 = new TextField("file:///Users/ivandolgolaptev/IdeaProjects/Laba1/src/main/java/utils/first.txt");
    private TextField urlField2 = new TextField("file:///Users/ivandolgolaptev/IdeaProjects/Laba1/src/main/java/utils/second.txt");
    private TextField searchField = new TextField();
    private DogBreedSearcher searcher = new DogBreedSearcher();

    public DogBreedSearchApp() {
        super("Dog Breed Search");
        setLayout(null);
        setBackground(new Color(199, 74, 116));
        setSize(600, 400);

        add(urlField1);
        add(urlField2);
        add(searchField);
        add(bex);
        add(sea);
        add(resultArea);

        urlField1.setBounds(20, 40, 550, 20);
        urlField2.setBounds(20, 70, 550, 20);
        searchField.setBounds(20, 100, 550, 20);
        bex.setBounds(220, 330, 100, 20);
        bex.addActionListener(this);
        sea.setBounds(220, 300, 100, 20);
        sea.addActionListener(this);
        resultArea.setBounds(20, 130, 550, 150);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == bex) {
            System.exit(0);
        } else if (ae.getSource() == sea) {
            String[] keywords = searchField.getText().split(",");
            for (int j = 0; j < keywords.length; j++) {
                keywords[j] = keywords[j].trim().toLowerCase();
            }
            String url1 = urlField1.getText().trim();
            String url2 = urlField2.getText().trim();
            Map.Entry<String, List<String>> bestMatch = searcher.searchBreedsInTwoFiles(url1, url2, keywords);

            resultArea.setText("");
            resultArea.append("Best match from file: " + bestMatch.getKey() + "\n");
            for (String match : bestMatch.getValue()) {
                resultArea.append(match + "\n");
            }
        }
    }

    public static void main(String[] args) {
        new DogBreedSearchApp();
    }
}