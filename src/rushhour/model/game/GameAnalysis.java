package rushhour.model.game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import rushhour.model.state.SixLongs;
import rushhour.model.state.State;
import rushhour.tools.FileTools;
import rushhour.tools.Tools;
import rushhour.view.GridPanel;

public class GameAnalysis {
	private final String file;
	private int size;
	private final String name;
	private final GridPanel grid;
	private String imgPath;
	private final int[][] intGrid;
	
	public GameAnalysis(String file, String name) {
		this.file = file;
		this.name = name;
		State state = new SixLongs();
		grid = new GridPanel(state.getGrid(), null);
		grid.setSize(430,430);
		FileTools.loadFile(state, file);
		grid.update(state.getGrid());
		intGrid = state.getGrid();
		try {
		    BufferedImage bi = getSnapshot(); // retrieve image
		    File dir = new File(".");
		    imgPath = dir.getPath() +"/../../" + "preview.png";
		    File outputfile = new File(imgPath);
		    imgPath = outputfile.toURI().toString();
		    ImageIO.write(bi, "png", outputfile);
		} catch (IOException e) {
		   
		}
	}
	
	private BufferedImage getSnapshot() {
	    return grid.getBufferedImage();
	}

	public String getFile() {
		return file;
	}

	public int getSize() {
		return size;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public String toHTML() {
		String html = "<html>\n";
		html += "\t<Body>\n";
		html += "\t\t<img width=\"200\" height=\"200\" src=\"" + imgPath + "\">";
		html += "\t</body>\n";
		html += "</html>\n";
		return html;
	}
	
	public GridPanel getGridPanel() {
		return grid;
	}

	public int[][] getIntGrid() {
		return Tools.cloneGrid(intGrid);
	}
}
