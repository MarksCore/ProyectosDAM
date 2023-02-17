using BusquedaParejas.Idiomas;
using BusquedaParejas.Properties;
using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Globalization;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;
using static System.Windows.Forms.VisualStyles.VisualStyleElement;

namespace BusquedaParejas
{
    public partial class Principal : Form
    {

        List<PictureBox> listaPictureBox = new List<PictureBox>();
        List<string> listaTitulos = new List<string>();
        Random numeroRandom = new Random();
        List<int> indexList = new List<int>();
        BindingList<Jugador> ranking = new BindingList<Jugador>();

        int contAciertos;
        int contIntentos;
        int tiempoEntreImagenes;
        int indexPrimeraImagen;
        int indexSegundaImagen;
        int tiempoRestante;
        int tiempoInicial;
        String dificultad;

        bool juegoIniciado = false;
        bool facilActivada = false;
        bool mediaActivada = false;
        bool dificilActivada = false;
        bool nombreJugadorIntroducido = false;


        public Principal()
        {
            InitializeComponent();
        }

        private void Principal_Load(object sender, EventArgs e)
        {
            lblJugador.Text = Idioma.lblJugador;
            lblIdioma.Text = Idioma.lblIdioma;
            btnFacil.Text = Idioma.btnFacil;
            btnMedia.Text = Idioma.btnMedia;
            btnDificil.Text = Idioma.btnDificil;
            btnIniciar.Text = Idioma.btnIniciar;
            btnSalir.Text = Idioma.btnSalir;

            cbIdioma.Items.Add(Idioma.cbEspanol);
            cbIdioma.Items.Add(Idioma.cbIngles);

            cbIdioma.Text = "Español";
        }

        private void cbIdioma_SelectedIndexChanged(object sender, EventArgs e)
        {
            int index = cbIdioma.SelectedIndex;

            if (index == 0)
            {
                Thread.CurrentThread.CurrentUICulture = new CultureInfo("es-ES");

            }
            else if (index == 1)
            {
                Thread.CurrentThread.CurrentUICulture = new CultureInfo("en-US");

            }

            lblJugador.Text = Idioma.lblJugador;
            lblIdioma.Text = Idioma.lblIdioma;
            btnFacil.Text = Idioma.btnFacil;
            btnMedia.Text = Idioma.btnMedia;
            btnDificil.Text = Idioma.btnDificil;
            btnIniciar.Text = Idioma.btnIniciar;
            btnSalir.Text = Idioma.btnSalir;

        }

        public void agregarTitulosImagenes()
        {
            listaTitulos.Add("creacionAdan");
            listaTitulos.Add("elGrito");
            listaTitulos.Add("lasMeninas");
            listaTitulos.Add("monaLisa");
            listaTitulos.Add("nacimientoVenus");
            listaTitulos.Add("nocheEstrellada");
            listaTitulos.Add("persistenciaMemoria");
            listaTitulos.Add("saturnoDevorandoHijo");
        }

        public void aleatorizarOrdenTitulos(List<string> listaTitulos)
        {
            for (int i = listaTitulos.Count - 1; i > 0; i--)
            {
                int j = numeroRandom.Next(i + 1);
                string nombreImagen = listaTitulos[j];
                listaTitulos[j] = listaTitulos[i];
                listaTitulos[i] = nombreImagen;
            }
        }

        public void crearListaTitulosAleatoria()
        {
            agregarTitulosImagenes();
            agregarTitulosImagenes();
            aleatorizarOrdenTitulos(listaTitulos);
        }

        public void crearListaPictureBox()
        {
            listaPictureBox.Add(pictureBox1);
            listaPictureBox.Add(pictureBox2);
            listaPictureBox.Add(pictureBox3);
            listaPictureBox.Add(pictureBox4);
            listaPictureBox.Add(pictureBox5);
            listaPictureBox.Add(pictureBox6);
            listaPictureBox.Add(pictureBox7);
            listaPictureBox.Add(pictureBox8);
            listaPictureBox.Add(pictureBox9);
            listaPictureBox.Add(pictureBox10);
            listaPictureBox.Add(pictureBox11);
            listaPictureBox.Add(pictureBox12);
            listaPictureBox.Add(pictureBox13);
            listaPictureBox.Add(pictureBox14);
            listaPictureBox.Add(pictureBox15);
            listaPictureBox.Add(pictureBox16);
        }

        public void asociarImagenes(int index)
        {
            if (listaTitulos[index].Equals("creacionAdan"))
            {
                listaPictureBox[index].Image = Resources.creacionadan;
            }
            else if (listaTitulos[index].Equals("elGrito"))
            {
                listaPictureBox[index].Image = Resources.elgrito;
            }
            else if (listaTitulos[index].Equals("lasMeninas"))
            {
                listaPictureBox[index].Image = Resources.meninas;
            }
            else if (listaTitulos[index].Equals("monaLisa"))
            {
                listaPictureBox[index].Image = Resources.monalisa;
            }
            else if (listaTitulos[index].Equals("nacimientoVenus"))
            {
                listaPictureBox[index].Image = Resources.nacimientovenus;
            }
            else if (listaTitulos[index].Equals("nocheEstrellada"))
            {
                listaPictureBox[index].Image = Resources.nocheestrellada;
            }
            else if (listaTitulos[index].Equals("persistenciaMemoria"))
            {
                listaPictureBox[index].Image = Resources.persistenciamemoria;
            }
            else if (listaTitulos[index].Equals("saturnoDevorandoHijo"))
            {
                listaPictureBox[index].Image = Resources.saturnodevorandohijo;
            }
        }

        public void comprobarPareja(int index)
        {
            if (juegoIniciado)
            {
                asociarImagenes(index);

                indexList.Add(index);

                if (indexList.Count == 2)
                {
                    if (listaTitulos[indexList[0]].Equals(listaTitulos[indexList[1]]) && !listaPictureBox[indexList[0]].Equals(listaPictureBox[indexList[1]]))
                    {
                        //MessageBox.Show("Pareja encontrada");
                        listaPictureBox[indexList[0]].Enabled = false;
                        listaPictureBox[indexList[1]].Enabled = false;
                        contAciertos++;
                    }
                    else
                    {
                        indexPrimeraImagen = indexList[0];
                        indexSegundaImagen = indexList[1];
                        tiempoEntreImagenes = 1;
                        timer2.Enabled = true;
                        // lblTiempo.Text = tiempoEntreImagenes.ToString();

                    }
                    // MessageBox.Show(indexPrimeraImagen.ToString());
                    // MessageBox.Show(indexSegundaImagen.ToString());
                    contIntentos++;
                    indexList.Clear();

                }
            }
            else
            {
                MessageBox.Show("Introduce un nombre de jugador, elige una dificultad y pulsa el botón Iniciar.",
                    "Datos necesarios",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Information);
            }
        }

        private void btnFacil_Click(object sender, EventArgs e)
        {
            facilActivada = true;
            mediaActivada = false;
            dificilActivada = false;
            dificultad = btnFacil.Text;
            if (facilActivada && !timer1.Enabled)
            {
                tiempoInicial = 120;
                tiempoRestante = 120;
                lblTiempo.Text = tiempoRestante.ToString();
            }
            else
            {
                MessageBox.Show("No es posible modificar la dificultad en medio de la partida.",
                    "La dificultad ya ha sido seleccionada",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Stop);
            }
        }

        private void btnMedia_Click(object sender, EventArgs e)
        {
            mediaActivada = true;
            facilActivada = false;
            dificilActivada = false;
            dificultad = btnMedia.Text;
            if (mediaActivada && !timer1.Enabled)
            {
                tiempoInicial = 60;
                tiempoRestante = 60;
                lblTiempo.Text = tiempoRestante.ToString();
            }
            else
            {
                MessageBox.Show("No es posible modificar la dificultad en medio de la partida.",
                    "La dificultad ya ha sido seleccionada",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Stop);
            }
        }

        private void btnDificil_Click(object sender, EventArgs e)
        {
            dificilActivada = true;
            facilActivada = false;
            mediaActivada = false;
            dificultad = btnDificil.Text;
            if (dificilActivada && !timer1.Enabled)
            {
                tiempoInicial = 30;
                tiempoRestante = 30;
                lblTiempo.Text = tiempoRestante.ToString();
            }
            else
            {
                MessageBox.Show("No es posible modificar la dificultad en medio de la partida.",
                    "La dificultad ya ha sido seleccionada",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Stop);
            }

        }

        private void btnIniciar_Click(object sender, EventArgs e)
        {
            epNombreJugador.Clear();
            if (tbNombreJugador.Text.Length == 0)
            {
                epNombreJugador.SetError(tbNombreJugador, "Es necesario introducir un nombre de jugador.");
            }
            else
            {
                nombreJugadorIntroducido = true;
            }


            if ((facilActivada || mediaActivada || dificilActivada) && nombreJugadorIntroducido)
            {
                if (!juegoIniciado)
                {
                    for (int i = 0; i < listaPictureBox.Count; i++)
                    {
                        listaPictureBox[i].Image = Resources.interrogacion;
                        listaPictureBox[i].Enabled = true;
                    }
                    crearListaTitulosAleatoria();
                    crearListaPictureBox();
                }

                tbNombreJugador.ReadOnly = true;
                juegoIniciado = true;
                timer1.Enabled = true;

            }
            else if (!nombreJugadorIntroducido)
            {
                MessageBox.Show("Introduce un nombre de jugador antes de empezar.",
                    "Nombre de jugador no introducido",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Information);
            }
            else if (!(facilActivada || mediaActivada || dificilActivada))
            {
                MessageBox.Show("Selecciona una dificultad antes de empezar.",
                    "Dificultad no seleccionada",
                    MessageBoxButtons.OK,
                    MessageBoxIcon.Information);
            }
        }

        private void reiniciarVariablesPartida()
        {
            contAciertos = 0;
            contIntentos = 0;
            juegoIniciado = false;
            facilActivada = false;
            mediaActivada = false;
            dificilActivada = false;
            nombreJugadorIntroducido = false;
            tbNombreJugador.ReadOnly = false;
            tbNombreJugador.Text = "";
            listaTitulos.Clear();
        }

        private void crearRanking()
        {
            ranking.Add(new Jugador
            {
                Dificultad = dificultad,
                Nombre = tbNombreJugador.Text,
                Intentos = contIntentos,
                Aciertos = contAciertos,
                Tiempo = (tiempoInicial - tiempoRestante)
            });

            dgvRanking.DataSource = ranking;
        }

        public void finalizarPartida()
        {
            if (contAciertos == 8 && juegoIniciado)
            {
                timer1.Stop();
                MessageBox.Show("¡Enhorabuena, han sido encontradas todas las parejas! Has realizado " + contIntentos + " intentos en un tiempo de " + (tiempoInicial - tiempoRestante) + " segundos.");

                for (int i = 0; i < listaPictureBox.Count; i++)
                {
                    listaPictureBox[i].Enabled = false;
                }

                crearRanking();

                reiniciarVariablesPartida();
            }
        }

        private void pictureBox1_Click(object sender, EventArgs e)
        {
            comprobarPareja(0);
            finalizarPartida();
        }

        private void pictureBox2_Click(object sender, EventArgs e)
        {
            comprobarPareja(1);
            finalizarPartida();
        }

        private void pictureBox3_Click(object sender, EventArgs e)
        {
            comprobarPareja(2);
            finalizarPartida();
        }

        private void pictureBox4_Click(object sender, EventArgs e)
        {
            comprobarPareja(3);
            finalizarPartida();
        }

        private void pictureBox5_Click(object sender, EventArgs e)
        {
            comprobarPareja(4);
            finalizarPartida();
        }

        private void pictureBox6_Click(object sender, EventArgs e)
        {
            comprobarPareja(5);
            finalizarPartida();
        }

        private void pictureBox7_Click(object sender, EventArgs e)
        {
            comprobarPareja(6);
            finalizarPartida();
        }

        private void pictureBox8_Click(object sender, EventArgs e)
        {
            comprobarPareja(7);
            finalizarPartida();
        }

        private void pictureBox9_Click(object sender, EventArgs e)
        {
            comprobarPareja(8);
            finalizarPartida();
        }

        private void pictureBox10_Click(object sender, EventArgs e)
        {
            comprobarPareja(9);
            finalizarPartida();
        }

        private void pictureBox11_Click(object sender, EventArgs e)
        {
            comprobarPareja(10);
            finalizarPartida();
        }

        private void pictureBox12_Click(object sender, EventArgs e)
        {
            comprobarPareja(11);
            finalizarPartida();
        }

        private void pictureBox13_Click(object sender, EventArgs e)
        {
            comprobarPareja(12);
            finalizarPartida();
        }

        private void pictureBox14_Click(object sender, EventArgs e)
        {
            comprobarPareja(13);
            finalizarPartida();
        }

        private void pictureBox15_Click(object sender, EventArgs e)
        {
            comprobarPareja(14);
            finalizarPartida();
        }

        private void pictureBox16_Click(object sender, EventArgs e)
        {
            comprobarPareja(15);
            finalizarPartida();
        }       

        private void btnSalir_Click(object sender, EventArgs e)
        {
            MessageBox.Show("¡Hasta pronto!");
            this.Close();
        }

        private void timer1_Tick(object sender, EventArgs e)
        {
            tiempoRestante--;
            lblTiempo.Text = tiempoRestante.ToString();
            if (tiempoRestante == 0)
            {
                timer1.Stop();
                MessageBox.Show("Fin del juego. Ha finalizado el tiempo. Has conseguido " + contAciertos + " aciertos en " + contIntentos + " intentos.");

                for (int i = 0; i < listaPictureBox.Count; i++)
                {
                    listaPictureBox[i].Enabled = false;
                }

                crearRanking();

                reiniciarVariablesPartida();
            }
        }

        private void timer2_Tick(object sender, EventArgs e)
        {
            tiempoEntreImagenes--;
            // lblTiempo.Text = tiempoEntreImagenes.ToString();
            if (tiempoEntreImagenes == 0)
            {
                listaPictureBox[indexPrimeraImagen].Image = Resources.interrogacion;
                listaPictureBox[indexSegundaImagen].Image = Resources.interrogacion;
                timer2.Stop();

            }
        }

        private void dgvRanking_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            dgvRanking.Columns["Intentos"].SortMode = DataGridViewColumnSortMode.Programmatic;
            //dgvRanking.SortOrder = SortOrder.Descending;
        }
    }
}
