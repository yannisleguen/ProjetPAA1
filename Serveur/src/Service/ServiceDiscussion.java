package Service;

import java.net.Socket;

public class ServiceDiscussion extends Service {
	//service où les utilisateurs discutent entre eux
	//contrainte : seulement deux utilisateurs peuvent discuter à la fois 
	// si quelqu'un tente de discuter avec un utilisateur occupé ils recoivent une notification 
	// il faut une liste permettant de bloquer à 2 user 
	// il peut y avoir plusieurs discussions en même temps 
	

	public ServiceDiscussion(Socket client,String user) {
		super(client);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
