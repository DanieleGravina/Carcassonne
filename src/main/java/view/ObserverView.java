package view;

import java.util.Map;


import common.Colors;
import common.CoordinatesTile;
import common.ErrorEvent;
import common.UpdateGameFinishedEvent;
import common.Listener;
import common.NumberOfPlayerErrorEvent;
import common.ObservableModel;
import common.UpdateFinalEvent;
import common.UpdateFinalObject;
import common.UpdateInitEvent;
import common.UpdateInitFirstEvent;
import common.UpdateInitFirstObject;
import common.UpdateInitObject;
import common.UpdatePieceEvent;
import common.UpdateRotationEvent;
import common.UpdateTileEvent;
/**
 * classe decorator di View per aggiungere la funzionalità di essere notificata dai cambiamenti del model
 * @author Daniele Iamartino, Daniele Gravina
 *
 */
public class ObserverView implements View, Listener{
	
	private View myView;
	
	public ObserverView(View view, ObservableModel gameModel){
		myView = view;
		gameModel.addListener(this);
	}

	@Override
	public void start() {
		myView.start();
		
	}
	
	@Override
	public void updateInitFirst(UpdateInitFirstObject obj){
		myView.updateInitFirst(obj);
	}
	
	@Override
	public void updateTile(CoordinatesTile tile) {
        myView.updateTile(tile);
		
	}
	
	@Override
	public void updatePiece(CoordinatesTile tile) {
		myView.updatePiece(tile);
		
	}
	
	@Override
	public void updateInit(UpdateInitObject obj){
		myView.updateInit(obj);
	}
	
	@Override
    public void error(Exception e){
		myView.error(e);
	}
	
	public void errorNumberOfPlayers(Exception e){
		if(myView instanceof LocalBasicView){
			((LocalBasicView) myView).errorNumberOfPlayers(e);
		}
	}
	
	@Override
	public void updateFinal(UpdateFinalObject obj){
		myView.updateFinal(obj);
	}
	
	@Override
	public void updateFinished(Map<Colors,Integer> finalPoints){
		myView.updateFinished(finalPoints);
	}
	
	@Override
    public void notifyUpdateInit(UpdateInitEvent event){
    	updateInit((UpdateInitObject)event.getSource());
    }
    

	@Override
	public void notifyError(ErrorEvent event) {
		error((Exception)event.getSource());
		
	}

	@Override
	public void notifyUpdateRotate(UpdateRotationEvent updateEvent) {
		updateRotation((CoordinatesTile)updateEvent.getSource());
		
	}

	@Override
	public void notifyUpdateTile(UpdateTileEvent updateEvent) {
		updateTile((CoordinatesTile)updateEvent.getSource());
		
	}

	@Override
	public void notifyUpdatePiece(UpdatePieceEvent updateEvent) {
		updatePiece((CoordinatesTile)updateEvent.getSource());
		
	}

	@Override
	public void notifyUpdateFinal(UpdateFinalEvent updateFinalEvent) {
		updateFinal((UpdateFinalObject)updateFinalEvent.getSource());
		
	}

	@Override
	public void notifyUpdateFinish(UpdateGameFinishedEvent updateGameFinishedEvent) {
		updateFinished(((Map<Colors,Integer>)updateGameFinishedEvent.getSource())); 
	}

	@Override
	public void updateRotation(CoordinatesTile tile) {
		myView.updateRotation(tile);
		
	}

	@Override
	public void notifyerrorNumberOfPlayers(
			NumberOfPlayerErrorEvent updateErrorEvent) {
		errorNumberOfPlayers((Exception) updateErrorEvent.getSource());
	}

	@Override
	public void notifyUpdateInitFirst(
			UpdateInitFirstEvent event) {
		updateInitFirst((UpdateInitFirstObject) event.getSource());
		
	}

}
