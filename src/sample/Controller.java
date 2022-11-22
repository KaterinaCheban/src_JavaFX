package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Controller {

    @FXML
    private ToggleGroup answers;

    @FXML
    private Text question_text;

    @FXML
    private RadioButton radio_btn_1;

    @FXML
    private RadioButton radio_btn_2;

    @FXML
    private RadioButton radio_btn_3;

    @FXML
    private RadioButton radio_btn_4;

    @FXML
    private Button answerBtn;

    private Questions[] questions = new Questions[] {
            new Questions("Как указать комментарий?", new String[] {"/* здесь комментарий", "/* здесь комментарий", "/* здесь комментарий /*", "// здесь комментарий"}),
            new Questions("Как подключить стандартную библиотеку iostream?", new String[] {"#include “iostream.h”;", "#include <iostream.h>;", "#include iostream;", "#include <iostream>;"}),
            new Questions("Сколько параметров можно передать в деструктор?", new String[] {"Не более 10", "Не более 15", "Максимум 1", "Нельзя передавать параметры в деструктор"}),
            new Questions("Каким будет x?\n" +
                    "int x = 2 + 1;", new String[] {"Будет ошибка", "1", "0", "3"}),
            new Questions("Где правильно указана переменная?", new String[] {"var str = \"Hi\";", "int num = \"1\";", "float x = 32,14;", "char sym = 'a';"}),
            new Questions("Что покажет код ниже?\n" + "char s[] = \"C++\"; " + "cout << s << \" \"; " + "s++; " + "cout << s << \" \";", new String[] {"C++ C++", "C++", "C++ ++", "Ошибку"}),
            new Questions("Отметьте правильные утверждения", new String[] {"конструкторы класса наследуются", "конструкторов класса может быть несколько, их синтаксис определяется программистом", "конструктор возвращает указатель на объект", "конструкторов класса может быть несколько, но их синтаксис должен подчиняться правилам перегрузки функций"})
    };

    private int nowQuestion = 0, correctAnswers;
    private String nowCorrectAnswer;


    @FXML
    public void initialize() {
        nowCorrectAnswer = questions[nowQuestion].correctAnswer();

        answerBtn.setOnAction(e -> {
            RadioButton selectedRadioButton = (RadioButton) answers.getSelectedToggle();
            if(selectedRadioButton != null) {
                String toogleGroupValue = selectedRadioButton.getText();

                if(toogleGroupValue.equals(nowCorrectAnswer)) {
                    System.out.println("Верный ответ");
                    correctAnswers++;
                } else {
                    System.out.println("Не верный ответ");
                }

                // Это был последний вопрос
                if(nowQuestion + 1 == questions.length) {
                    radio_btn_1.setVisible(false);
                    radio_btn_2.setVisible(false);
                    radio_btn_3.setVisible(false);
                    radio_btn_4.setVisible(false);
                    answerBtn.setVisible(false);

                    question_text.setText("Вы ответили корректно на " + correctAnswers + " из " + questions.length + " вопросов!");
                } else {
                    nowQuestion++;
                    nowCorrectAnswer = questions[nowQuestion].correctAnswer();

                    question_text.setText(questions[nowQuestion].getQuestion());
                    String[] answers = questions[nowQuestion].getAnswers();


                    List<String> intList = Arrays.asList(answers);

                    Collections.shuffle(intList);

                    radio_btn_1.setText(intList.get(0));
                    radio_btn_2.setText(intList.get(1));
                    radio_btn_3.setText(intList.get(2));
                    radio_btn_4.setText(intList.get(3));

                    selectedRadioButton.setSelected(false);
                }

            }
        });
    }

}
