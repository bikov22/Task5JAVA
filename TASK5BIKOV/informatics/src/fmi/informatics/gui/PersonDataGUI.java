package fmi.informatics.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ConnectionBuilder;
import java.util.Arrays;

import javax.swing.*;

import fmi.informatics.comparators.*;
import fmi.informatics.extending.Person;
import fmi.informatics.extending.Professor;
import fmi.informatics.extending.Student;

// създаваме клас PersonDataGUI
public class PersonDataGUI {
	
	public static Person[] people;
	JTable table;
	PersonDataModel personDataModel;

	public static void main(String[] args) {
		getPeople();
		PersonDataGUI gui = new PersonDataGUI();
		gui.createAndShowGUI();
	}
	
	public static void getPeople() {
		people = new Person[8];
		
		for (int i = 0; i < 4; i++) {
			Person student = Student.StudentGenerator.make();
			people[i] = student;
		}
		
		for (int i = 4; i < 8; i++) {
			Person professor = Professor.ProfessorGenerator.make();
			people[i] = professor;
		}
	}


	public void createAndShowGUI() {
		JFrame frame = new JFrame("Таблица с данни за хора");
		frame.setSize(500, 500);
		
		JLabel label = new JLabel("Списък с потребители", JLabel.CENTER);
		
		personDataModel = new PersonDataModel(people);
		table = new JTable(personDataModel);
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		// Добавяме бутон за сортиране по години с Comparable interface
		JButton buttonSortAge = new JButton("Сортирай по Години в низходящ ред");
		JButton buttonSortEgn = new JButton("Сортирай по Егн в низходящ ред");
		JButton buttonSortName = new JButton("Сортирай по Име в низходящ ред");
		JButton buttonSortWeight = new JButton("Сортирай по Тегло в низходящ ред");
		JButton buttonSortHeight = new JButton("Сортирай по Височина в низходящ ред");

		// Добавяме бутон за сортиране
		JButton buttonSort = new JButton("Сортирай");
		
		Container container = frame.getContentPane();
		container.setLayout(new BorderLayout());
		container.add(label, BorderLayout.NORTH);
		container.add(scrollPane, BorderLayout.CENTER);
		JButton[] buttons = new JButton[5];
		buttons[0] = buttonSortAge;
		buttons[1] = buttonSortEgn;
		buttons[2] = buttonSortName;
		buttons[3] = buttonSortWeight;
		buttons[4] = buttonSortHeight;
		JOptionPane paneBtns = new JOptionPane(buttons);
		container.add(paneBtns, BorderLayout.BEFORE_FIRST_LINE);
		container.add(buttonSort, BorderLayout.SOUTH);
		
		// Добавяме listener към бутона за сортиране по години
		buttonSortName.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DescendingSortButtons(1, table, people);
				table.repaint();
			}
		});
		buttonSortEgn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DescendingSortButtons(2, table, people);
				table.repaint();
			}
		});
		buttonSortHeight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DescendingSortButtons(3, table, people);
				table.repaint();
			}
		});
		buttonSortWeight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DescendingSortButtons(4, table, people);
				table.repaint();
			}
		});
		buttonSortAge.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DescendingSortButtons(5, table, people);
				table.repaint();
			}
		});

		// Добавяме диалог
		final JDialog sortDialog = new CustomDialog(getSortText(), this);
		
		// Добавяме listener към бутона за сортиране
		buttonSort.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sortDialog.pack();
				sortDialog.setVisible(true);
			}
		});
		
		frame.setVisible(true);
	}
	
	public void sortTable(int intValue, JTable table, Person[] people) {
		PersonComparator comparator = null;
		
		switch (intValue) {
			case 1: 
				comparator = new NameComparator(); 
				break;
			case 2: 
				comparator = new EgnComparator();
				break;
			case 3:
				comparator = new HeightComparator();
				break;
			case 4: 
				comparator = new WeightComparator();
				break;
			case 5:
				comparator = new AgeComparator();
				break;
		}

		if (comparator == null) { // Ако стойността е null - сортирай по подразбиране
			Arrays.sort(people);
		} else {
			Arrays.sort(people, comparator);
		}

		table.repaint();	
	}
	
	private static String getSortText() {
		return "Моля, въведете цифрата на колоната, по която да се сортират данните: \n" +
				" 1 - Име \n" +
				" 2 - ЕГН \n" +
				" 3 - Височина \n" +
				" 4 - Тегло \n" +
				" 5 - Години \n"; 
	}



	public void DescendingSortButtons(int intValue, JTable table, Person[] people) {
		PersonComparator comparator = null;
		switch (intValue) {
			case 1:
				comparator = new NameComparator();
				break;
			case 2:
				comparator = new EgnComparator();
				break;
			case 3:
				comparator = new HeightComparator();
				break;
			case 4:
				comparator = new WeightComparator();
				break;
			case 5:
				comparator = new AgeComparator();
				break;
		}

		if (comparator == null) { // Ако стойността е null - сортирай по подразбиране
			Arrays.sort(people);
		} else {
			comparator.setOrderToDescending();
			Arrays.sort(people, comparator);
		}

		table.repaint();
	}
}
