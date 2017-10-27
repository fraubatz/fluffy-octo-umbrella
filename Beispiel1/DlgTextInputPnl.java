import java.awt.*;
import java.awt.event.*;


public class DlgTextInputPnl extends Panel implements ActionListener,
													  AdjustmentListener,
													  ItemListener
													  
{
	private Label			_inputLabel;
	private TextField		_inputField;
	private Button 			_buttonOK;
	private Button 			_buttonCancel;
	private Button			_buttonOptions;

	private Dialog			_dlg;
	private SmMainFrame		_mainFrame;
	
	public DlgTextInputPnl(Dialog dlg, SmMainFrame frame)
	{
		_dlg		= dlg;
		_mainFrame	= frame;

		setLayout (null);

		_inputLabel = new Label ("Ihr Text :");
		_inputLabel.setBounds (10, 11, 56, 21);
		add (_inputLabel);

		_inputField = new TextField ("");
		_inputField.addActionListener (this);
		_inputField.setBounds (10, 34, 264, 21);
		_inputField.selectAll();
		add (_inputField);

		_buttonOK = new Button ("OK");
		_buttonOK.addActionListener (this);
		_buttonOK.setBounds (10, 70, 75, 21);
		add (_buttonOK);

		_buttonCancel = new Button ("Abbrechen");
		_buttonCancel.addActionListener (this);
		_buttonCancel.setBounds (91, 70, 75, 21);
		add (_buttonCancel);

		_buttonOptions = new Button ("Optionen");
		_buttonOptions.addActionListener (this);
		_buttonOptions.setBounds (200, 70, 75, 21);
		add (_buttonOptions);
	}

	public Dimension getPreferredSize()
	{
		return new Dimension (287, 103);
	}

	public void	actionPerformed(ActionEvent evt)
	{
		if (evt.getSource () == _buttonOK)
		{
			 SmPanelCenter _center = _mainFrame.getCurrentCenter();
			 SmWorkingArea _workingArea = _center.getWorkingArea();
			 _workingArea.createText(_inputField.getText());
			 _dlg.dispose();
			  return;
		}
		if (evt.getSource () == _buttonCancel)
		{
			 _dlg.dispose();
			  return;
		}
		if (evt.getSource () == _inputField)
		{
			  return;
		}
		if (evt.getSource () == _buttonOptions)
		{
			DlgText d = new DlgText(_mainFrame, true);
			d.show();
	        return;
		}
	}

	public void	adjustmentValueChanged(AdjustmentEvent evt)
	{

	}

	public void	itemStateChanged(ItemEvent evt)
	{

	}
} 