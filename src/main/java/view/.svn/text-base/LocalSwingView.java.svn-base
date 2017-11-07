package view;

import common.UpdateInitFirstObject;

import controller.Controller;

/**
 * View swing locale
 * @author Daniele Gravina, Daniele Iamartino
 *
 */
public class LocalSwingView extends SwingView {
	

	public LocalSwingView(Controller controller) {
		super(controller);
	}
	
	@Override
	public void updateInitFirst(UpdateInitFirstObject obj) {
		swingBoard.putTileToBoard(obj.getFirstTile());
		updateInit(obj.getInitObject());
		swingBoard.addPlayers(obj);
		swingBoard.drawBoard();
		swingBoard.setVisible(true);
	}

}
