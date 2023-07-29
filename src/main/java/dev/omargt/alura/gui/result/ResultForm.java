package dev.omargt.alura.gui.result;

import java.awt.Dimension;

import javax.swing.JDialog;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Window;

import javax.swing.JTextField;
import javax.swing.JButton;

public class ResultForm extends JDialog {
	
	private static final long serialVersionUID = 1L;
	
	private JTextField txtResult;
	private JTextField txtUnitRateTo;
	private JTextField txtDateDescription;
	private JLabel lblFrom;
	private JLabel lblTo;
	private JLabel lblAmount;
	private JLabel lblUnitRateTo;
	private JLabel  lblDateDescription;
	private JButton btnClose;

	/**
	 * Create the dialog.
	 */
	public ResultForm(Window parent, String title, String from,
			String to, String amount, String result,
			String unitRateTo, String dateDescription) {
		super(parent);
        setTitle(title);
		setLocationRelativeTo(parent);
        setMinimumSize(new Dimension(500, 220));

        buildElements();
        
        lblFrom.setText(from);
        lblTo.setText(to);
        lblAmount.setText(amount);
        txtResult.setText(result);

        if(unitRateTo != null){
            lblUnitRateTo.setVisible(true);
            txtUnitRateTo.setVisible(true);
            txtUnitRateTo.setText(unitRateTo);
        }

        if(dateDescription != null){
            lblDateDescription.setVisible(true);
            txtDateDescription.setVisible(true);
            txtDateDescription.setText(dateDescription);
        }

        // Close form
        btnClose.addActionListener(e -> dispose());

        setVisible(true);

	}
	
	private void buildElements() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("From:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		lblFrom = new JLabel("New label");
		GridBagConstraints gbc_lblFrom = new GridBagConstraints();
		gbc_lblFrom.anchor = GridBagConstraints.WEST;
		gbc_lblFrom.insets = new Insets(0, 0, 5, 0);
		gbc_lblFrom.gridx = 1;
		gbc_lblFrom.gridy = 0;
		getContentPane().add(lblFrom, gbc_lblFrom);
		
		JLabel lblNewLabel_2 = new JLabel("To:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 1;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		lblTo = new JLabel("New label");
		GridBagConstraints gbc_lblTo = new GridBagConstraints();
		gbc_lblTo.anchor = GridBagConstraints.WEST;
		gbc_lblTo.insets = new Insets(0, 0, 5, 0);
		gbc_lblTo.gridx = 1;
		gbc_lblTo.gridy = 1;
		getContentPane().add(lblTo, gbc_lblTo);
		
		JLabel lblNewLabel_4 = new JLabel("Amount:");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 2;
		getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		lblAmount = new JLabel("New label");
		GridBagConstraints gbc_lblAmount = new GridBagConstraints();
		gbc_lblAmount.anchor = GridBagConstraints.WEST;
		gbc_lblAmount.insets = new Insets(0, 0, 5, 0);
		gbc_lblAmount.gridx = 1;
		gbc_lblAmount.gridy = 2;
		getContentPane().add(lblAmount, gbc_lblAmount);
		
		JLabel lblNewLabel_6 = new JLabel("Result:");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 3;
		getContentPane().add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		txtResult = new JTextField();
		txtResult.setEditable(false);
		GridBagConstraints gbc_txtResult = new GridBagConstraints();
		gbc_txtResult.insets = new Insets(0, 0, 5, 0);
		gbc_txtResult.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtResult.gridx = 1;
		gbc_txtResult.gridy = 3;
		getContentPane().add(txtResult, gbc_txtResult);
		txtResult.setColumns(10);
		
		lblUnitRateTo = new JLabel("Unit Rate To:");
		lblUnitRateTo.setVisible(false);
		GridBagConstraints gbc_lblUnitRateTo = new GridBagConstraints();
		gbc_lblUnitRateTo.anchor = GridBagConstraints.WEST;
		gbc_lblUnitRateTo.insets = new Insets(0, 0, 5, 5);
		gbc_lblUnitRateTo.gridx = 0;
		gbc_lblUnitRateTo.gridy = 4;
		getContentPane().add(lblUnitRateTo, gbc_lblUnitRateTo);
		
		txtUnitRateTo = new JTextField();
		txtUnitRateTo.setEditable(false);
		txtUnitRateTo.setVisible(false);
		GridBagConstraints gbc_txtUnitRateTo = new GridBagConstraints();
		gbc_txtUnitRateTo.insets = new Insets(0, 0, 5, 0);
		gbc_txtUnitRateTo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUnitRateTo.gridx = 1;
		gbc_txtUnitRateTo.gridy = 4;
		getContentPane().add(txtUnitRateTo, gbc_txtUnitRateTo);
		txtUnitRateTo.setColumns(10);
		
		lblDateDescription = new JLabel("Date:");
		lblDateDescription.setVisible(false);
		GridBagConstraints gbc_lblDateDescription = new GridBagConstraints();
		gbc_lblDateDescription.anchor = GridBagConstraints.WEST;
		gbc_lblDateDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblDateDescription.gridx = 0;
		gbc_lblDateDescription.gridy = 5;
		getContentPane().add(lblDateDescription, gbc_lblDateDescription);
		
		txtDateDescription = new JTextField();
		txtDateDescription.setEditable(false);
		txtDateDescription.setVisible(false);
		GridBagConstraints gbc_txtDateDescription = new GridBagConstraints();
		gbc_txtDateDescription.insets = new Insets(0, 0, 5, 0);
		gbc_txtDateDescription.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDateDescription.gridx = 1;
		gbc_txtDateDescription.gridy = 5;
		getContentPane().add(txtDateDescription, gbc_txtDateDescription);
		txtDateDescription.setColumns(10);
		
		btnClose = new JButton("Close");
		GridBagConstraints gbc_btnClose = new GridBagConstraints();
		gbc_btnClose.insets = new Insets(0, 0, 0, 5);
		gbc_btnClose.gridx = 0;
		gbc_btnClose.gridy = 8;
		getContentPane().add(btnClose, gbc_btnClose);
	}

}
