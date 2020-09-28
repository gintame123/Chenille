import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

/**
class qui permet de desinner.
*/
public class Gameplay extends JPanel
{
	private static int HAUTEUR = 800;
	private static int LONGUEUR = 700;

	/**
	fonction qui permet de dessiner la chenille, la pomme et les prhase avec le score et le temps
	@param g : est un composant graphics
	*/

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		Chenille chenille = Chenille.chenille;

		g.setColor(Color.BLACK);

		g.fillRect(0, 0, HAUTEUR, LONGUEUR);

		g.setColor(Color.BLUE);

		for (Point point : chenille.corps)
		{
			g.fillOval(point.x * Chenille.ESPACE, point.y * Chenille.ESPACE, Chenille.ESPACE, Chenille.ESPACE);
		}

		g.fillOval(chenille.tete.x * Chenille.ESPACE, chenille.tete.y * Chenille.ESPACE, Chenille.ESPACE, Chenille.ESPACE);

		g.setColor(Color.RED);

		g.fillOval(chenille.pommes.x * Chenille.ESPACE, chenille.pommes.y * Chenille.ESPACE, Chenille.ESPACE, Chenille.ESPACE);

		String string = "Score : " + chenille.score + ", Taille de la Chenille : " + chenille.taille_corps + ", Temps : " + chenille.time / 20;

		g.setColor(Color.white);

		g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), 10);

		string = "Game Over!";

		if (chenille.fin)
		{
			g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), (int) chenille.dim.getHeight() / 4);
		}

		string = "Pause!";

		if (chenille.pause && !chenille.fin)
		{
			g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), (int) chenille.dim.getHeight() / 4);
		}
	}
}
