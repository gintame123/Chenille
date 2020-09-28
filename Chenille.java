
import java.awt.Dimension;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

/**Classe qui modelise une interface comportant de ma chenille.
*/
public class Chenille implements ActionListener, KeyListener
{

	public static Chenille chenille;

	public JFrame fen;

	public Gameplay gameplay;

	public Timer timer = new Timer(20, this);

	private static int HAUTEUR = 800;
	private static int LONGUEUR = 700;

	public ArrayList<Point> corps = new ArrayList<Point>();

	public static final int HAUT = 0, BAS = 1, GAUCHE = 2, DROITE = 3, ESPACE = 10;

	public int nbr = 0, direction = BAS, score, taille_corps = 10, time;

	public Point tete, pommes, dir;

	public Random random;

	public boolean fin = false, pause;

	public Dimension dim;

	private JMenuBar MenuPricipale = new JMenuBar();
	private JMenu MenuAide = new JMenu("Aide");
	private JMenuItem NouveauJeu = new JMenuItem("Nouveau Jeu");
	private JMenuItem Quitter = new JMenuItem("Quitter");
	private JMenuItem Controle = new JMenuItem("Touche");
	private JMenuItem Apropos = new JMenuItem("à propos ?");
	private JOptionPane Information = new JOptionPane();


	/**Constructeur de la Chenille.
  */
	public Chenille()
	{
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		fen = new JFrame("Chenille");
		fen.setVisible(true);
		fen.setSize(HAUTEUR, LONGUEUR + 50);
		fen.setResizable(false);
		fen.setLocation(dim.width / 2 - fen.getWidth() / 2, dim.height / 2 - fen.getHeight() / 2);
		fen.add(gameplay = new Gameplay());
		fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fen.addKeyListener(this);

		MenuPricipale.add(MenuAide);
    MenuPricipale.add(NouveauJeu);
    MenuPricipale.add(Quitter);
    MenuAide.add(Controle);
    MenuAide.add(Apropos);
    fen.setJMenuBar(MenuPricipale);
    NouveauJeu.addActionListener(new ActionListener() {
			/**
      écoute l'évènement pour lancer une nouvelle Partie.
      @param e : une action de la part de l'utilisateur
      */
			public void actionPerformed(ActionEvent e) {
				score = 0;
				Lancement();
			}
		});

			Quitter.addActionListener(new ActionListener() {
				/**
	      écoute l'évènement pour quitter le jeu.
	      @param e : une action de la part de l'utilisateur
	      */

			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

  		Controle.addActionListener(new ActionListener() {
				/**
	      écoute l'évènement pour lancer les informations utiles de déplacements de touche.
	      @param e : une action de la part de l'utilisateur
	      */

  			public void actionPerformed(ActionEvent e) {
  				Information.showMessageDialog(null,"Haut -> déplacez en haut\n Bas -> déplacez en Bas \nDroite -> déplacez à droite\n Gauche -> déplacez à gauche\n p   -> Pause","Information",JOptionPane.INFORMATION_MESSAGE);
  			}
  		});
  		Apropos.addActionListener(new ActionListener() {
				/**
	      écoute l'évènement pour lancer les informations de la créations du jeu.
	      @param e : une action de la part de l'utilisateur
	      */
  			public void actionPerformed(ActionEvent e) {

  				JOptionPane.showMessageDialog(null,"Fait par Ayoub Chakhite\n"+"Etudiant : \n"+"La faculté de Jean Perrin \n"+"année 2019/2020"," Informations ", JOptionPane.INFORMATION_MESSAGE);
  			}
  		});

    fen.setJMenuBar(MenuPricipale);
		Lancement();
	}
/*
Permet de lancer la parie
*/
	public void Lancement()
	{
		fin = false;
		pause = false;
		time = 0;
		score = 0;
		taille_corps = 4;
		nbr = 0;
		direction = BAS;
		tete = new Point(0, -1);
		random = new Random();
		corps.clear();
		pommes = new Point(random.nextInt(78), random.nextInt(66));
		timer.start();
	}


	@Override
	/**
	terrain avec mur
	Permet de vérifier le bon fonctionnement du Jeu
	c'est a dire si c'est la fin de la partie, et de gerer les déplacements
	@param arg0 : écoute l'évènement de l'utilisateur
	*/
/*	public void actionPerformed(ActionEvent arg0)
	{
		gameplay.repaint();
		nbr++;

		if (nbr % 2 == 0 && tete != null && !fin && !pause)
		{
			time++;

			corps.add(new Point(tete.x, tete.y));

			if (direction == HAUT)
			{

				if (tete.y - 1 >= 0 && taille_queue(tete.x, tete.y - 1))
				{
					tete = new Point(tete.x, tete.y - 1);
				}

				else
				{
					fin = true;

				}
			}

			if (direction == BAS)
			{
				if (tete.y + 1 < LONGUEUR / 10 && taille_queue(tete.x, tete.y + 1))//67
				{
					tete = new Point(tete.x, tete.y + 1);
				}
				else
				{
					fin = true;
				}
			}

			if (direction == GAUCHE)
			{
				if (tete.x - 1 >= 0 && taille_queue(tete.x - 1, tete.y))
				{
					tete = new Point(tete.x - 1, tete.y);
				}
				else
				{
					fin = true;
				}
			}

			if (direction == DROITE)
			{
				if (tete.x + 1 < HAUTEUR/10 && taille_queue(tete.x + 1, tete.y))
				{
					tete = new Point(tete.x + 1, tete.y);
				}
				else
				{
					fin = true;
				}
			}

			if (corps.size() > taille_corps)
			{
				corps.remove(0);

			}

			if (pommes != null)
			{
				if (tete.equals(pommes))
				{
					score += 10;
					taille_corps++;
					pommes.setLocation(random.nextInt(79), random.nextInt(66));
				}
			}
		}
	}
*/

/**
terrain sans mur
*/
	public void actionPerformed(ActionEvent arg0)
	{
		gameplay.repaint();
		nbr++;

		if (nbr % 2 == 0 && tete != null && !fin && !pause)
		{
			time++;

			corps.add(new Point(tete.x, tete.y));

			if (direction == HAUT)
   {
    if (tete.y - 1 < 0){
        tete = new Point(tete.x, tete.y + LONGUEUR / ESPACE);
    }else{
        if (tete.y - 1 >= 0 && taille_queue(tete.x, tete.y - 1))
        {
             tete = new Point(tete.x, tete.y - 1);
        }
        else
        {
            fin = true;
        }
       }
    }


			if (direction == BAS)
			{
				if (tete.y + 1 < 0){
		        tete = new Point(tete.x , tete.y + LONGUEUR / ESPACE);
				}else{
					if (tete.y + 1 < LONGUEUR / 10 && taille_queue(tete.x, tete.y + 1))//67
				{
					tete = new Point(tete.x, tete.y + 1);
				}
				else
				{
					fin = true;
				}
			}
			}

			if (direction == GAUCHE)
			{
				if (tete.x - 1 < 0) {
					tete = new Point(tete.x + HAUTEUR / ESPACE, tete.y);
				}else{
				if (tete.x - 1 >= 0 && taille_queue(tete.x - 1, tete.y))
				{
					tete = new Point(tete.x - 1, tete.y);
				}
				else
				{
					fin = true;
				}
			}
			}

			if (direction == DROITE)
			{
				if (tete.x + 1 >= HAUTEUR/10 ) {
					tete = new Point(tete.x - HAUTEUR / ESPACE, tete.y);
				}else{
				if (tete.x + 1 < HAUTEUR/10 && taille_queue(tete.x + 1, tete.y))
				{
					tete = new Point(tete.x + 1, tete.y);
				}
				else
				{
					fin = true;
				}
			}
			}

			if (corps.size() > taille_corps)
			{
				corps.remove(0);

			}

			if (pommes != null)
			{
				if (tete.equals(pommes))
				{
					score += 10;
					taille_corps++;
					pommes.setLocation(random.nextInt(79), random.nextInt(66));
				}
			}
		}
	}



	/**
	permet de vérifier si la queue ne touche pas la tete ou signiler la fin de partie par un true ou false
	@param x : l'abscisse
	@param y : l'ordonée
	@return : true ou false selon si la chenille est vivante ou pas
	*/
	public boolean taille_queue(int x, int y)
	{
		for (Point point : corps)
		{
			if (point.equals(new Point(x, y)))
			{
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args)
	{
		chenille = new Chenille();
	}

	@Override

	/** fonction qui permet d'ecouter l'évènement quand on appuie sur une touche
	 @param e : écoute l'évènement de l'utilisateur
	 */
	public void keyPressed(KeyEvent e)
	{
		int i = e.getKeyCode();

		if ((i == KeyEvent.VK_Q || i == KeyEvent.VK_LEFT) && direction != DROITE)
		{
			direction = GAUCHE;
		}

		if ((i == KeyEvent.VK_D || i == KeyEvent.VK_RIGHT) && direction != GAUCHE)
		{
			direction = DROITE;
		}

		if ((i == KeyEvent.VK_Z || i == KeyEvent.VK_UP) && direction != BAS)
		{
			direction = HAUT;
		}

		if ((i == KeyEvent.VK_W || i == KeyEvent.VK_DOWN) && direction != HAUT)
		{
			direction = BAS;
		}

		if (i == KeyEvent.VK_SPACE)
		{
			if (fin)
			{
				Lancement();
			}
			else
			{
				pause = !pause;
			}
		}
	}

	@Override
	/**
	fonction qui permet d'écouter quand l'utilisateur relache la touche qui à utiliser
	@param e : écoute l'évènement de l'utilisateur
	*/
	public void keyReleased(KeyEvent e)
	{
	}

	@Override
	/**
	fonction qui permet d'écouter quand l'utilisateur enfonce la touche qui à utiliser
	@param e : écoute l'évènement de l'utilisateur
	*/
	public void keyTyped(KeyEvent e)
	{
	}

}
