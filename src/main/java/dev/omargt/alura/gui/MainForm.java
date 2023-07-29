package dev.omargt.alura.gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import dev.omargt.alura.converter.CurrencyServiceException;
import dev.omargt.alura.converter.CurrencyWrapper;
import dev.omargt.alura.converter.ExchangerCurrency;
import dev.omargt.alura.converter.unit.Length;
import dev.omargt.alura.converter.unit.TemperatureScale;
import dev.omargt.alura.gui.result.builders.ResultFormBuilder;

import java.awt.GridBagLayout;
import javax.swing.JTabbedPane;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Insets;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import java.awt.Color;
import java.awt.Font;

public class MainForm extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm frame = new MainForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/****** ELEMENTS ***********/
	private JTabbedPane tbpMain;
	private JPanel contentPane;
	private JFormattedTextField txtCurrencyAmount;
	private JComboBox<CurrencyWrapper> cboFromCurrency;
	private JComboBox<CurrencyWrapper> cboToCurrency;
	private JLabel lblServiceMessage;
	private JFormattedTextField txtLengthAmount;
	private JComboBox<Length> cboFromLength;
	private JComboBox<Length> cboToLength;
	private JFormattedTextField txtTemperatureAmount;
	private JComboBox<TemperatureScale> cboFromTemperature;
	private JComboBox<TemperatureScale> cboToTemperature;
	private JButton btnConverter;
	/****** END ELEMENTS ***********/
	
	
    private final ResultFormBuilder builderResultForm;

    // Tab Panel
    enum TabPanelSection {
        CURRENCY, // First
        LENGTH, // Second
        TEMPERATURE // Third
    }

    private TabPanelSection tabSelected;


	/**
	 * Create the frame.
	 */
	public MainForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Alura Converter");
		setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(650, 400));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		buildElements();

		builderResultForm = new ResultFormBuilder();
        tabSelected = TabPanelSection.CURRENCY; // Default selected
        
        inputFormats();
        listeners();

        // Show the form
        setVisible(true);

        loadCurrencies(); // This is async
        loadLengths();
        loadTemperatures();
	}
	
	private void buildElements() {
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNewLabel_1 = new JLabel("Alura Converter");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_1.setForeground(new Color(36, 31, 49));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		tbpMain = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tbpMain = new GridBagConstraints();
		gbc_tbpMain.insets = new Insets(0, 0, 5, 0);
		gbc_tbpMain.fill = GridBagConstraints.BOTH;
		gbc_tbpMain.gridx = 0;
		gbc_tbpMain.gridy = 1;
		contentPane.add(tbpMain, gbc_tbpMain);
		
		JPanel panel = new JPanel();
		tbpMain.addTab("Currency", null, panel, null);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel = new JLabel("Amount to exchange:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		txtCurrencyAmount = new JFormattedTextField();
		txtCurrencyAmount.setEnabled(false);
		GridBagConstraints gbc_txtCurrencyAmount = new GridBagConstraints();
		gbc_txtCurrencyAmount.insets = new Insets(0, 0, 5, 0);
		gbc_txtCurrencyAmount.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCurrencyAmount.gridx = 1;
		gbc_txtCurrencyAmount.gridy = 0;
		panel.add(txtCurrencyAmount, gbc_txtCurrencyAmount);
		
		JLabel lblNewLabel_2 = new JLabel("From:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 1;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		cboFromCurrency = new JComboBox<>();
		cboFromCurrency.setEnabled(false);
		GridBagConstraints gbc_cboFromCurrency = new GridBagConstraints();
		gbc_cboFromCurrency.insets = new Insets(0, 0, 5, 0);
		gbc_cboFromCurrency.fill = GridBagConstraints.HORIZONTAL;
		gbc_cboFromCurrency.gridx = 1;
		gbc_cboFromCurrency.gridy = 1;
		panel.add(cboFromCurrency, gbc_cboFromCurrency);
		
		JLabel lblNewLabel_3 = new JLabel("To:");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 2;
		panel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		cboToCurrency = new JComboBox<>();
		cboToCurrency.setEnabled(false);
		GridBagConstraints gbc_cboToCurrency = new GridBagConstraints();
		gbc_cboToCurrency.insets = new Insets(0, 0, 5, 0);
		gbc_cboToCurrency.fill = GridBagConstraints.HORIZONTAL;
		gbc_cboToCurrency.gridx = 1;
		gbc_cboToCurrency.gridy = 2;
		panel.add(cboToCurrency, gbc_cboToCurrency);
		
		lblServiceMessage = new JLabel("ServiceMessage");
		lblServiceMessage.setVisible(false);
		GridBagConstraints gbc_lblServiceMessage = new GridBagConstraints();
		gbc_lblServiceMessage.anchor = GridBagConstraints.WEST;
		gbc_lblServiceMessage.gridwidth = 2;
		gbc_lblServiceMessage.insets = new Insets(0, 0, 0, 5);
		gbc_lblServiceMessage.gridx = 0;
		gbc_lblServiceMessage.gridy = 3;
		panel.add(lblServiceMessage, gbc_lblServiceMessage);
		
		JPanel panel_1 = new JPanel();
		tbpMain.addTab("Length", null, panel_1, null);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblNewLabel_4 = new JLabel("Amount to convert:");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 0;
		panel_1.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		txtLengthAmount = new JFormattedTextField();
		GridBagConstraints gbc_txtLengthAmount = new GridBagConstraints();
		gbc_txtLengthAmount.insets = new Insets(0, 0, 5, 0);
		gbc_txtLengthAmount.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtLengthAmount.gridx = 1;
		gbc_txtLengthAmount.gridy = 0;
		panel_1.add(txtLengthAmount, gbc_txtLengthAmount);
		
		JLabel lblNewLabel_5 = new JLabel("From:");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 1;
		panel_1.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		cboFromLength = new JComboBox<>();
		GridBagConstraints gbc_cboFromLength = new GridBagConstraints();
		gbc_cboFromLength.insets = new Insets(0, 0, 5, 0);
		gbc_cboFromLength.fill = GridBagConstraints.HORIZONTAL;
		gbc_cboFromLength.gridx = 1;
		gbc_cboFromLength.gridy = 1;
		panel_1.add(cboFromLength, gbc_cboFromLength);
		
		JLabel lblNewLabel_6 = new JLabel("To:");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 2;
		panel_1.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		cboToLength = new JComboBox<>();
		GridBagConstraints gbc_cboToLength = new GridBagConstraints();
		gbc_cboToLength.fill = GridBagConstraints.HORIZONTAL;
		gbc_cboToLength.gridx = 1;
		gbc_cboToLength.gridy = 2;
		panel_1.add(cboToLength, gbc_cboToLength);
		
		JPanel panel_2 = new JPanel();
		tbpMain.addTab("Temperature", null, panel_2, null);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel lblNewLabel_7 = new JLabel("Amount to convert:");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_7.gridx = 0;
		gbc_lblNewLabel_7.gridy = 0;
		panel_2.add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		txtTemperatureAmount = new JFormattedTextField();
		GridBagConstraints gbc_txtTemperatureAmount = new GridBagConstraints();
		gbc_txtTemperatureAmount.insets = new Insets(0, 0, 5, 0);
		gbc_txtTemperatureAmount.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTemperatureAmount.gridx = 1;
		gbc_txtTemperatureAmount.gridy = 0;
		panel_2.add(txtTemperatureAmount, gbc_txtTemperatureAmount);
		
		JLabel lblNewLabel_8 = new JLabel("From:");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 0;
		gbc_lblNewLabel_8.gridy = 1;
		panel_2.add(lblNewLabel_8, gbc_lblNewLabel_8);
		
		cboFromTemperature = new JComboBox<>();
		GridBagConstraints gbc_cboFromTemperature = new GridBagConstraints();
		gbc_cboFromTemperature.insets = new Insets(0, 0, 5, 0);
		gbc_cboFromTemperature.fill = GridBagConstraints.HORIZONTAL;
		gbc_cboFromTemperature.gridx = 1;
		gbc_cboFromTemperature.gridy = 1;
		panel_2.add(cboFromTemperature, gbc_cboFromTemperature);
		
		JLabel lblNewLabel_9 = new JLabel("To:");
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_9.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_9.gridx = 0;
		gbc_lblNewLabel_9.gridy = 2;
		panel_2.add(lblNewLabel_9, gbc_lblNewLabel_9);
		
		cboToTemperature = new JComboBox<>();
		GridBagConstraints gbc_cboToTemperature = new GridBagConstraints();
		gbc_cboToTemperature.fill = GridBagConstraints.HORIZONTAL;
		gbc_cboToTemperature.gridx = 1;
		gbc_cboToTemperature.gridy = 2;
		panel_2.add(cboToTemperature, gbc_cboToTemperature);
		
		btnConverter = new JButton("Convert");
		GridBagConstraints gbc_btnConverter = new GridBagConstraints();
		gbc_btnConverter.gridx = 0;
		gbc_btnConverter.gridy = 2;
		contentPane.add(btnConverter, gbc_btnConverter);
		
	}
	
    private void inputFormats() {
        // Only decimal format
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        NumberFormatter numberFormatter = new NumberFormatter(decimalFormat);
        // Create the formatter factory with the NumberFormatter
        DefaultFormatterFactory formatterFactory = new DefaultFormatterFactory(numberFormatter);

        txtCurrencyAmount.setFormatterFactory(formatterFactory);
        txtCurrencyAmount.setValue(1);// Default value

        txtLengthAmount.setFormatterFactory(formatterFactory);
        txtLengthAmount.setValue(1); // Default value

        txtTemperatureAmount.setFormatterFactory(formatterFactory);
        txtTemperatureAmount.setValue(1); // Default value
    }

    private void listeners() {
        tbpMain.addChangeListener(e -> tabSelected = TabPanelSection.values()[tbpMain.getSelectedIndex()]);

        btnConverter.addActionListener(e -> {
            switch (tabSelected) {
                case CURRENCY:
                    if(txtCurrencyAmount.isEnabled())
                        convertCurrencies();
                    break;
                case LENGTH:
                    convertLengths();
                    break;
                case TEMPERATURE:
                    convertTemperatures();
                    break;
                default:
                    JOptionPane.showMessageDialog(this, tabSelected.name());

            }
        });
    }



    private void loadCurrencies() {
        setMessageToCurrencyLabel("Loading...", Color.GREEN);

        try {
            ExchangerCurrency.getCurrencies()
                    .thenAccept(currencies -> {
                        // Loading combos
                        currencies.forEach(cboFromCurrency::addItem);
                        currencies.forEach(cboToCurrency::addItem);

                        lblServiceMessage.setVisible(false);
                        enableCurrencyInputs(true);
                    });
        } catch (CurrencyServiceException e) {
            setMessageToCurrencyLabel(e.getMessage(), Color.RED);
            enableCurrencyInputs(true);
        }
    }

    private void loadLengths() {
        cboFromLength.addItem(Length.METER);
        cboFromLength.addItem(Length.KILOMETER);
        cboFromLength.addItem(Length.CENTIMETER);
        cboFromLength.addItem(Length.MILLIMETER);
        cboFromLength.addItem(Length.INCH);
        cboFromLength.addItem(Length.FOOT);

        cboToLength.addItem(Length.METER);
        cboToLength.addItem(Length.KILOMETER);
        cboToLength.addItem(Length.CENTIMETER);
        cboToLength.addItem(Length.MILLIMETER);
        cboToLength.addItem(Length.INCH);
        cboToLength.addItem(Length.FOOT);
    }

    private void loadTemperatures() {
        cboFromTemperature.addItem(TemperatureScale.CELSIUS);
        cboFromTemperature.addItem(TemperatureScale.FAHRENHEIT);
        cboFromTemperature.addItem(TemperatureScale.KELVIN);
        cboFromTemperature.addItem(TemperatureScale.RANKINE);


        cboToTemperature.addItem(TemperatureScale.CELSIUS);
        cboToTemperature.addItem(TemperatureScale.FAHRENHEIT);
        cboToTemperature.addItem(TemperatureScale.KELVIN);
        cboToTemperature.addItem(TemperatureScale.RANKINE);
    }

    public void convertCurrencies(){
        setMessageToCurrencyLabel("Loading...", Color.GREEN);
        enableCurrencyInputs(false);

        CurrencyWrapper currencyFrom = (CurrencyWrapper) cboFromCurrency.getSelectedItem();
        CurrencyWrapper currencyTo = (CurrencyWrapper) cboToCurrency.getSelectedItem();
        double amount = Double.parseDouble(txtCurrencyAmount.getValue().toString());

        try {
            ExchangerCurrency.exchange(currencyFrom.getCurrency(), currencyTo.getCurrency(), amount)
                    .thenAccept(exchangeResult -> {
                        builderResultForm
                                .setContainer(this)
                                .setTitle("Exchange currency result")
                                .setFrom(currencyFrom.toString())
                                .setTo(currencyTo.toString())
                                .setAmount(String.valueOf(amount))
                                .setResult(String.valueOf(exchangeResult.getResult()))
                                .setUnitRateTo(String.valueOf(exchangeResult.getUnitRateTo()))
                                .setDateDescription(exchangeResult.getDate().toString())
                                .build();

                        lblServiceMessage.setVisible(false);
                        enableCurrencyInputs(true);
                    });
        } catch (CurrencyServiceException ex) {
            setMessageToCurrencyLabel(ex.getMessage(), Color.RED);
            enableCurrencyInputs(true);
        }
    }

    private void convertLengths() {
        Length lengthFrom = (Length) cboFromLength.getSelectedItem();
        Length lengthTo = (Length) cboToLength.getSelectedItem();
        double amount = Double.parseDouble(txtLengthAmount.getValue().toString());

        double result = lengthFrom.convertTo(lengthTo, amount);

        builderResultForm
                .setContainer(this)
                .setTitle("Length conversion result")
                .setFrom(lengthFrom.toString())
                .setTo(lengthTo.toString())
                .setAmount(String.valueOf(amount))
                .setResult(String.valueOf(result))
                .build();
    }

    private void convertTemperatures() {
        TemperatureScale temperatureScaleFrom = (TemperatureScale) cboFromTemperature.getSelectedItem();
        TemperatureScale temperatureScaleTo = (TemperatureScale) cboToTemperature.getSelectedItem();
        double amount = Double.parseDouble(txtTemperatureAmount.getValue().toString());

        double result = temperatureScaleFrom.convertTo(temperatureScaleTo, amount);

        builderResultForm
                .setContainer(this)
                .setTitle("Temperature conversion result")
                .setFrom(temperatureScaleFrom.toString())
                .setTo(temperatureScaleTo.toString())
                .setAmount(String.valueOf(amount))
                .setResult(String.valueOf(result))
                .build();
    }

    private void setMessageToCurrencyLabel(String message, Color color) {
        lblServiceMessage.setForeground(color);
        lblServiceMessage.setText(message);
        lblServiceMessage.setVisible(true);
    }

    private void enableCurrencyInputs(boolean enable) {
        txtCurrencyAmount.setEnabled(enable);
        cboFromCurrency.setEnabled(enable);
        cboToCurrency.setEnabled(enable);
    }


}
