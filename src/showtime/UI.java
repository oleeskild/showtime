package showtime;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

import engine.HtmlParser;
import engine.Services;
import engine.TvShow;

import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.jsoup.nodes.Document;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Label;

public class UI {

	protected Shell shell;
	private Text text;
	private List showList;
	private CTabFolder tabFolder;
	private Browser infoBr;
	private Label lblDesc;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UI window = new UI();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(931, 664);
		shell.setText("Showtime");
		shell.setLayout(null);

		showList = new List(shell, SWT.BORDER);
		showList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				selectShow(showList, tabFolder, infoBr, lblDesc);
			}
		});
		showList.setBounds(0, 33, 258, 595);

		text = new Text(shell, SWT.BORDER);
		text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// 13 = enter
				if (e.keyCode == 13) {
					search(text, showList);
				}
			}
		});
		text.setBounds(0, 0, 172, 27);

		Button btnSearch = new Button(shell, SWT.NONE);
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				search(text, showList);
			}

		});
		btnSearch.setBounds(176, 0, 82, 27);
		btnSearch.setText("Search");

		tabFolder = new CTabFolder(shell, SWT.BORDER);
		tabFolder.setBounds(265, 385, 652, 243);
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));

		infoBr = new Browser(shell, SWT.NONE);
		infoBr.setBounds(264, 10, 255, 369);
		infoBr.setVisible(false);

		lblDesc = new Label(shell, SWT.WRAP);
		lblDesc.setBounds(536, 99, 381, 266);

	}

	private static void search(Text text, List showList) {
		if (!text.getText().equals("")) {
			TvShow show = engine.Services.findShowByName(
					engine.Showtime.getShowList(), text.getText());
			text.setText("");
			if (show != null) {
				showList.add(show.getName());
			}
		}
	}

	private static void selectShow(List showList, final CTabFolder tabFolder,
			Browser image, Label info) {
		TvShow selShow = Services.findShowByName(engine.Showtime.getShowList(),
				showList.getSelection()[0]);

		Document showDoc = HtmlParser.getShowDoc(selShow);
		selShow = Services.sortSeasons(HtmlParser.getMagnetLinks(showDoc),
				selShow);

		// element 0 == image, element 1 == description
		String[] tvShowInfo = selShow.getInfo();

		if (tvShowInfo[1] != null) {
			info.setText(tvShowInfo[1]);
		} else {
			info.setText("kunne ikke finne beskrivelse");
		}

		String htmlDoc = "<html><body bgcolor=\"grey\"><img src=\""
				+ tvShowInfo[0]
				+ "\" width=\"253\" height=\"359\" style=\"width:253px; height:359; margin-left:-8; margin-top:-7;\"\\></body></html>";

		image.setText(htmlDoc);
		image.setVisible(true);

		for (CTabItem item : tabFolder.getItems()) {
			item.dispose();
		}

		// Makes the season tabs
		for (int i = selShow.seasonLength() - 1; i > 0; i--) {

			CTabItem item = new CTabItem(tabFolder, SWT.CLOSE);

			item.setText("Season " + (selShow.getSeasonArr()[i].getSeasonNr()));
			final List epList = new List(tabFolder, SWT.V_SCROLL);
			final TvShow newShow = selShow;
			for (int j = 0; j < selShow.getSeason(
					selShow.getSeasonArr()[i].getSeasonNr()).episodeLength(); j++) {
				epList.add("Episode " + (j + 1));
			}

			epList.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseDoubleClick(MouseEvent e) {
					Video vidForm = new Video();
					vidForm.playVideo(
							newShow,
							Integer.parseInt(tabFolder.getSelection().getText()
									.replace("Season ", "")),
							epList.getSelectionIndex() + 1);
					vidForm.open();
				}
			});

			item.setControl(epList);
		}

	}
}
