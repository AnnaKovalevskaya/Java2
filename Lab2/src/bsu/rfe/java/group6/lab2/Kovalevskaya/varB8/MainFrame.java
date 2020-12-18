package bsu.rfe.java.group6.lab2.Kovalevskaya.varB8;

//Импортируются классы, используемые в приложении
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class MainFrame extends JFrame {
	
	// Размеры окна приложения в виде констант
    private static final int WIDTH = 500;
    private static final int HEIGHT = 320;

    private static double mem1;
    private static double mem2;
    private static double mem3;

 // Текстовые поля для считывания значений переменных,
 // как компоненты, совместно используемые в различных методах
    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
 // Текстовое поле для отображения результата,
 // как компонент, совместно используемый в различных методах
    private JTextField textFieldResult;
    private JTextField textFieldMem1;
    private JTextField textFieldMem2;
    private JTextField textFieldMem3;


 // Группа радио-кнопок для обеспечения уникальности выделения в группе
    private ButtonGroup radioButtons = new ButtonGroup();
 // Контейнер для отображения радио-кнопок
    private Box hboxFormulaType = Box.createHorizontalBox();
    private ButtonGroup radioButtons2 = new ButtonGroup();
    Box hboxVariablesType = Box.createHorizontalBox();


    private int formulaId = 1;
    private int variableId = 1;

 // Формула №1 для рассчѐта
    public Double calculate1(Double x, Double y, Double z) {
        return Math.pow((Math.log10(Math.pow((1 + x), 2)) + Math.cos(Math.PI * Math.pow(z, 3))), Math.sin(y)) + Math.pow((Math.pow(Math.E, Math.pow(x, 2)) + Math.cos(Math.pow(Math.E, z) + Math.sqrt(1 / y))), 1 / x);
    }
 // Формула №2 для рассчѐта
    public Double calculate2(Double x, Double y, Double z) {
        return y * ((Math.pow(x, 2)) / (Math.log10(Math.pow(z, y)) + Math.pow(Math.cos(Math.cbrt(x)), 2)));
    }

 // Вспомогательный метод для добавления кнопок на панель
 // buttonName – текст рядом с кнопкой, formulaId – идентификатор формулы
    private void addRadioButton(String buttonName, final int formulaId) {
    	// Создать экземпляр радио-кнопки с заданным текстом
    	JRadioButton button = new JRadioButton(buttonName);
    	// Определить и зарегистрировать обработчик
    	button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
            	// Который будет устанавливать идентификатор выбранной
            	// формулы в классе Formula равным formulaId
                MainFrame.this.formulaId = formulaId;
                JButton imagePane = new JButton();
                imagePane.updateUI();
            }
        });
    	// Добавить радио-кнопку в группу
    	radioButtons.add(button);
    	// Добавить радио-кнопку в контейнер
    	// Для этого ссылка на контейнер сделана полем данных класса
        hboxFormulaType.add(button);
    }

    private void addRadioButton2(String buttonName, final int variableId) {
        JRadioButton button2 = new JRadioButton(buttonName);
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                MainFrame.this.variableId = variableId;
                JButton imagePane = new JButton();
                imagePane.updateUI();
            }
        });
        radioButtons2.add(button2);
        hboxVariablesType.add(button2);
    }


 // Конструктор класса
    public MainFrame()   {
        super("Calculation of a formula");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
     // Отцентрировать окно приложения на экране
        setLocation((kit.getScreenSize().width - WIDTH) / 2, (kit.getScreenSize().height - HEIGHT) / 2);

     // Добавить «клей» C1-H1 с левой стороны
        hboxFormulaType.add(Box.createHorizontalGlue());
     // Создать радио-кнопку для формулы 1
        addRadioButton("Formula 1", 1);
     // Создать радио-кнопку для формулы 2
        addRadioButton("Formula 2", 2);
     // Установить выделенной 1-ую кнопку из группы
        radioButtons.setSelected(radioButtons.getElements().nextElement().getModel(), true);
     // Добавить «клей» C1-H2 с правой стороны
        hboxFormulaType.add(Box.createHorizontalGlue());
     // Задать рамку для коробки с помощью класса BorderFactory
        hboxFormulaType.setBorder(BorderFactory.createLineBorder(Color.YELLOW));


        hboxVariablesType.add(Box.createHorizontalGlue());
        addRadioButton2("Variable 1", 1);

        textFieldMem1 = new JTextField("0", 15);
        textFieldMem1.setMaximumSize(textFieldMem1.getPreferredSize());
        hboxVariablesType.add(Box.createHorizontalStrut(10));
        hboxVariablesType.add(textFieldMem1);
        textFieldMem1.setEditable(false);


        addRadioButton2("Variable 2", 2);

        textFieldMem2 = new JTextField("0", 15);
        textFieldMem2.setMaximumSize(textFieldMem2.getPreferredSize());
        hboxVariablesType.add(Box.createHorizontalStrut(10));
        hboxVariablesType.add(textFieldMem2);
        textFieldMem2.setEditable(false);


        addRadioButton2("Variable 3", 3);

        textFieldMem3 = new JTextField("0", 15);
        textFieldMem3.setMaximumSize(textFieldMem3.getPreferredSize());
        hboxVariablesType.add(Box.createHorizontalStrut(10));
        hboxVariablesType.add(textFieldMem3);
        textFieldMem3.setEditable(false);


        radioButtons2.setSelected(radioButtons2.getElements().nextElement().getModel(), true);
        hboxVariablesType.add(Box.createHorizontalGlue());
        hboxVariablesType.setBorder(BorderFactory.createLineBorder(Color.BLACK));

     // Создать область с полями ввода для X и Y
     // Создать подпись “X:” для переменной X
        JLabel labelForX = new JLabel("X:");
        // Создать текстовое поле для ввода значения переменной X,
        // (по умолчанию 0)
        textFieldX = new JTextField("0", 10);
     // Установить макс размер = желаемому для предотвращения масштабирования
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
     // Создать подпись “Y:” для переменной Y
        JLabel labelForY = new JLabel("Y:");
        // Создать текстовое поле для ввода значения переменной Y,
        // (по умолчанию 0)
        textFieldY = new JTextField("0", 10);
        // Установить макс размер = желаемому для предотвращения масштабирования
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        JLabel labelForZ = new JLabel("Z:");
     // Создать текстовое поле для ввода значения переменной Z,
        // (по умолчанию 0)
        textFieldZ = new JTextField("0", 10);
     // Установить макс размер = желаемому для предотвращения масштабирования
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());
     // Создать контейнер «коробка с горизонтальной укладкой»
        Box hboxVariables = Box.createHorizontalBox();
     // Задать рамку для коробки с помощью класса BorderFactory
        hboxVariables.setBorder(BorderFactory.createLineBorder(Color.RED));

     // Добавить в контейнер ряд объектов:
     // Добавить «клей» C2-H1 – для максимального удаления от левого края
        hboxVariables.add(Box.createHorizontalGlue());
     // Добавить подпись для переменной Х
        hboxVariables.add(labelForX);
     // Добавить «распорку» C2-H2 шириной 10 пикселов для отступа между
     // надписью и текстовым полем для ввода значения X
        hboxVariables.add(Box.createHorizontalStrut(10));
     // Добавить само текстовое поле для ввода Х
        hboxVariables.add(textFieldX);
     // Добавить «клей» C2-H5 для максимального удаления друг от друга
        hboxVariables.add(Box.createGlue());
        
        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldY);
        hboxVariables.add(Box.createGlue());
        
        hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldZ);
     // Добавить «клей» C2-H5 для максимального удаления от правого края
        //hboxVariables.add(Box.createHorizontalGlue());


     // Создать область для вывода результата
        JLabel labelForResult = new JLabel("Result:");
     // Создать текстовое поле для вывода результата, начальное значение - 0
        textFieldResult = new JTextField("0", 30);
        textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());
        textFieldResult.setEditable(false);
     // Создать контейнер «коробка с горизонтальной укладкой»
        Box hboxResult = Box.createHorizontalBox();
     // Добавить в контейнер ряд объектов
     // Добавить «клей» C3-H1 для отступа от левого края
        hboxResult.add(Box.createHorizontalGlue());
     // Добавить подпись для результата
        hboxResult.add(labelForResult);
        // Добавить «распорку» C3-H2 в 10 пикселов между подписью и полем
        // результата
        hboxResult.add(Box.createHorizontalStrut(10));
     // Добавить текстовое поле для вывода результата
        hboxResult.add(textFieldResult);
     // Добавить «клей» C3-H3 справа
        hboxResult.add(Box.createHorizontalGlue());
     // Задать рамку для контейнера
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));


     // Создать область для кнопок
        JButton buttonCalc = new JButton("Calculate");
     // Определить и зарегистрировать обработчик нажатия на кнопку
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
            	// Преобразование введенных строк в числа с плавающей точкой может
            	// спровоцировать исключительную ситуацию при неправильном формате чисел,
            	// поэтому необходим блок try-catch
                try {
                	// Получить значение X
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    mem1 = Double.parseDouble(textFieldMem1.getText());
                    mem2 = Double.parseDouble(textFieldMem2.getText());
                    mem3 = Double.parseDouble(textFieldMem3.getText());
                    Double result;
                 // Вычислить результат
                    if (formulaId == 1)
                        result = calculate1(x, y, z);
                    else result = calculate2(x, y, z);
                 // Установить текст надписи равным результату
                    textFieldResult.setText(result.toString());
                } catch (NumberFormatException ex) {
                	// В случае исключительной ситуации показать сообщение
                    JOptionPane.showMessageDialog(MainFrame.this, "Floating-point writing format error",
                            "Wrong number format", JOptionPane.WARNING_MESSAGE);
                }
            }
        });


        JButton buttonDel = new JButton("MC");
        buttonDel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if (variableId == 1) {
                    mem1 = 0;
                    textFieldMem1.setText(String.valueOf(mem1));
                }
                if (variableId == 2) {
                    mem2 = 0;
                    textFieldMem2.setText(String.valueOf(mem2));
                }
                if (variableId == 3) {
                    mem3 = 0;
                    textFieldMem3.setText(String.valueOf(mem3));
                }

            }
        });

        JButton buttonSum = new JButton("M+");
        buttonSum.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    mem1 = Double.parseDouble(textFieldMem1.getText());
                    mem2 = Double.parseDouble(textFieldMem2.getText());
                    mem3 = Double.parseDouble(textFieldMem3.getText());
                    Double result;
                    if (variableId == 1) {
                        if (formulaId == 1) result = mem1 + calculate1(x, y, z);
                        else result = mem1 + calculate2(x, y, z);
                        textFieldMem1.setText(String.valueOf(result));
                    }
                    if (variableId == 2) {
                        if (formulaId == 1) result = mem2 + calculate1(x, y, z);
                        else result = mem2 + calculate2(x, y, z);
                        textFieldMem2.setText(result.toString());
                    }
                    if (variableId == 3) {
                        if (formulaId == 1) result = mem3 + calculate1(x, y, z);
                        else result = mem3 + calculate2(x, y, z);
                        textFieldMem3.setText(result.toString());
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Floating-point writing format error",
                            "Wrong number format", JOptionPane.WARNING_MESSAGE);
                }

            }
        });

     // Создать кнопку «Очистить поля»
        JButton buttonReset = new JButton("Clear fields");
     // Определить и зарегистрировать обработчик нажатия на кнопку
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
                mem1 = 0;
                textFieldMem1.setText(String.valueOf(mem1));
                mem2 = 0;
                textFieldMem2.setText(String.valueOf(mem2));
                mem3 = 0;
                textFieldMem3.setText(String.valueOf(mem3));
            }
        });



     // Создать коробку с горизонтальной укладкой
        Box hboxButtons = Box.createHorizontalBox();
        // Добавить «клей» C4-H1 с левой стороны
        hboxButtons.add(Box.createHorizontalGlue());
     // Добавить кнопку «Вычислить» в компоновку
        hboxButtons.add(buttonCalc);
     // Добавить распорку в 30 пикселов C4-H2 между кнопками
        hboxButtons.add(Box.createHorizontalStrut(30));
     // Добавить кнопку «Очистить поля» в компоновку
        hboxButtons.add(buttonReset);
     // Добавить «клей» C4-H3 с правой стороны
        hboxButtons.add(Box.createHorizontalGlue());
     // Задать рамку для контейнера
        hboxButtons.setBorder(BorderFactory.createLineBorder(Color.GREEN));


        Box hboxButtonsM = Box.createHorizontalBox();
        hboxButtonsM.add(Box.createHorizontalGlue());
        hboxButtonsM.add(buttonDel);
        hboxButtonsM.add(Box.createHorizontalStrut(30));
        hboxButtonsM.add(buttonSum);
        hboxButtonsM.add(Box.createHorizontalGlue());
        hboxButtonsM.setBorder(BorderFactory.createLineBorder(Color.WHITE));


     // Связать области воедино в компоновке BoxLayout
     // Создать контейнер «коробка с вертикальной укладкой»
        Box contentBox = Box.createVerticalBox();
     // Добавить «клей» V1 сверху
        contentBox.add(Box.createVerticalGlue());
     // Добавить контейнер с выбором формулы
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxVariablesType);
     // Добавить контейнер с переменными
        contentBox.add(hboxVariables);
     // Добавить контейнер с результатом вычислений
        contentBox.add(hboxResult);
        contentBox.add(hboxButtonsM);
     // Добавить контейнер с кнопками
        contentBox.add(hboxButtons);
     // Добавить «клей» V2 снизу
        contentBox.add(Box.createVerticalGlue());
     // Установить «вертикальную коробку» в область содержания главного окна
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }


 // Главный метод класса
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}