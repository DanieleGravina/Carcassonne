package network;

import java.rmi.RemoteException;


import common.ErrorEvent;
import common.UpdateGameFinishedEvent;
import common.NumberOfPlayerErrorEvent;
import common.ReverseController;
import common.UpdateFinalEvent;
import common.UpdateInitEvent;
import common.UpdateInitFirstEvent;
import common.UpdatePieceEvent;
import common.UpdateRotationEvent;
import common.UpdateTileEvent;

/**
 * Implementazione di ServerView per RMI
 * @author Daniele Iamartino, Daniele Gravina
 *
 */
public class ServerViewRMI implements ServerView{
	
	private static final long serialVersionUID = 2812379676492333831L;
	private ReverseController reverseController;
	
	public ServerViewRMI(ReverseController reverseController){
		this.reverseController = reverseController;
	}

	@Override
	public void notifyUpdateInit(UpdateInitEvent event) {
		try {
			reverseController.notifyUpdateInit(event);
		} catch (RemoteException e) {
			System.out.print(e);
		}
	}

	@Override
	public void notifyUpdateRotate(UpdateRotationEvent event) {
		try {
			reverseController.notifyUpdateRotate(event);
		} 
		catch (RemoteException e) {
			System.out.print(e);
		}
	}

	@Override
	public void notifyUpdateTile(UpdateTileEvent event) {
		try {
			reverseController.notifyUpdateTile(event);
		} 
		catch (RemoteException e) {
			System.out.print(e);
		}
	}

	@Override
	public void notifyUpdatePiece(UpdatePieceEvent event) {
		try {
			reverseController.notifyUpdatePiece(event);
		} 
		catch (RemoteException e) {
			System.out.print(e);
		}
	}

	@Override
	public void notifyUpdateFinal(UpdateFinalEvent event) {
		try {
			reverseController.notifyUpdateFinal(event); 
		} 
		catch (RemoteException e) {
			System.out.print(e);
		}
	}

	@Override
	public void notifyUpdateFinish(UpdateGameFinishedEvent event) {
		try {
			reverseController.notifyUpdateFinish(event); 
		} 
		catch (RemoteException e) {
			System.out.print(e);
		}
	}

	@Override
	public void notifyerrorNumberOfPlayers(
			NumberOfPlayerErrorEvent event) {
		try {
			reverseController.notifyerrorNumberOfPlayers(event); 
		} 
		catch (RemoteException e) {
			System.out.print(e);
		}
	}

	@Override
	public void notifyError(ErrorEvent event) {
		try {
			reverseController.notifyError(event); 
		} 
		catch (RemoteException e) {
			System.out.print(e);
		}
	}

	@Override
	public void notifyUpdateInitFirst(
			UpdateInitFirstEvent event) {
		System.out.println("scatenato evento nella server view");
		try {
			reverseController.notifyUpdateInitFirst(event);
		} catch (RemoteException e) {
			System.out.print(e);
		}
	}

}
