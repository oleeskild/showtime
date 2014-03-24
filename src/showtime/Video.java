package showtime;

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import engine.TvShow;

public class Video {

	protected Shell shell;
	private Browser browser;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Video window = new Video();
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
		shell.setSize(801, 541);
		shell.setText("Showtime Video Player");

		browser = new Browser(shell, SWT.NONE);
		browser.setBounds(10, 0, 777, 505);
		// String html =
		// "<html><head><meta content=\"width=device-width; height=device-height;\" name=\"viewport\"></head><body marginheight=\"0\" marginwidth=\"0\"><embed type=\"video/x-matroska\" src=\"http://localhost:8888/\" name=\"plugin\" height=\"100%\" width=\"100%\"></body></html>";
		browser.setUrl("http://localhost:8888");
		// browser.setText(html);
	}

	public void playVideo(TvShow selShow, int season, int episode) {
		Runtime rt = Runtime.getRuntime();
		try {
			Process peerflix = rt
					.exec("peerflix "
							+ selShow.getSeason(season).getEpisode(episode)
									.getMagnet());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// browser.refresh();
	}
}
