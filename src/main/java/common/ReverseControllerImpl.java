package common;

import java.rmi.RemoteException;

import network.ClientModel;

public class ReverseControllerImpl implements ReverseController{
	
	private ClientModel clientModel;

	public ReverseControllerImpl(ClientModel clientModel){
		this.clientModel = clientModel;
	}
	
	@Override
	public void notifyUpdateInit(UpdateInitEvent event) throws RemoteException{
		clientModel.notifyUpdateInit(event);
	}
	
	@Override
	public void notifyUpdateRotate(UpdateRotationEvent event) throws RemoteException{
		clientModel.notifyUpdateRotate(event); 
	}
	
	@Override
	public void notifyUpdateTile(UpdateTileEvent event) throws RemoteException{
		clientModel.notifyUpdateTile(event); 
	}

	@Override
	public void notifyUpdatePiece(UpdatePieceEvent event) throws RemoteException{
		clientModel.notifyUpdatePiece(event);
	}

	@Override
	public void notifyUpdateFinal(UpdateFinalEvent event) throws RemoteException{
		clientModel.notifyUpdateFinal(event);
	}

	@Override
	public void notifyUpdateFinish(UpdateGameFinishedEvent event) throws RemoteException{
		clientModel.notifyUpdateFinish(event);
	}

	@Override
	public void notifyerrorNumberOfPlayers(
			NumberOfPlayerErrorEvent event) throws RemoteException {
		clientModel.notifyerrorNumberOfPlayers(event);
	}

	@Override
	public void notifyError(ErrorEvent event) throws RemoteException {
		clientModel.notifyError(event);
	}

	@Override
	public void notifyUpdateInitFirst(
			UpdateInitFirstEvent event) throws RemoteException {
		clientModel.notifyUpdateInitFirst(event);
	}

}
