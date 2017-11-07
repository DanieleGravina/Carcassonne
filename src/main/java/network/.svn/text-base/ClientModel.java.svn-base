package network;

import common.ErrorEvent;
import common.UpdateGameFinishedEvent;
import common.Listener;
import common.NumberOfPlayerErrorEvent;
import common.ObservableModel;
import common.UpdateFinalEvent;
import common.UpdateInitEvent;
import common.UpdateInitFirstEvent;
import common.UpdatePieceEvent;
import common.UpdateRotationEvent;
import common.UpdateTileEvent;


/**
 * Un model fittizio utilizzato lato client per simulare la ricezione di eventi dal model (e generarli invece in altro modo)
 * @author Daniele Iamartino, Daniele Gravina
 *
 */
public class ClientModel implements ObservableModel,Listener{
	
	private Listener listener = null;
	
	@Override
	public void addListener(Listener listener) {		
		this.listener = listener;	
	}


	@Override
	public void notifyUpdateInit(UpdateInitEvent updateEvent) {
		listener.notifyUpdateInit(updateEvent);
	}


	@Override
	public void notifyUpdateRotate(UpdateRotationEvent updateEvent) {
		listener.notifyUpdateRotate(updateEvent);
	}


	@Override
	public void notifyUpdateTile(UpdateTileEvent updateEvent) {
		listener.notifyUpdateTile(updateEvent);
	}


	@Override
	public void notifyUpdatePiece(UpdatePieceEvent updateEvent) {
		listener.notifyUpdatePiece(updateEvent);
	}


	@Override
	public void notifyUpdateFinal(UpdateFinalEvent updateFinalEvent) {
		listener.notifyUpdateFinal(updateFinalEvent);
	}


	@Override
	public void notifyUpdateFinish(UpdateGameFinishedEvent updateFinalEvent) {
		listener.notifyUpdateFinish(updateFinalEvent);
	}


	@Override
	public void notifyerrorNumberOfPlayers(
			NumberOfPlayerErrorEvent updateErrorEvent) {
		listener.notifyerrorNumberOfPlayers(updateErrorEvent);
	}


	@Override
	public void notifyError(ErrorEvent event) {
		listener.notifyError(event);
	}


	@Override
	public void notifyUpdateInitFirst(
			UpdateInitFirstEvent updateInitFirstEvent) {
		listener.notifyUpdateInitFirst(updateInitFirstEvent);
	}
	

}
