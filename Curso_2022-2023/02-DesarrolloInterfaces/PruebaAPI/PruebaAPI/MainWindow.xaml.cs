using RestSharp;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace PruebaAPI
{
    public partial class MainWindow : Window
    {
        List<OfertaJuego> listaOfertas;
        List<OfertaJuego> listaOfertasPorPrecio;
        int contOferta = 0;
        int numPagina = 1;
        int totalPaginas = 0;
        float precioMaximo = 0f;
        float precioMinimo = 0f;


        public MainWindow()
        {
            InitializeComponent();
            var client = new RestClient("https://www.cheapshark.com");
            var request = new RestRequest("/api/1.0/deals?storeID=1", Method.Get);
            listaOfertas = client.Execute<List<OfertaJuego>>(request).Data;

            foreach (OfertaJuego oferta in listaOfertas)
            {
                if (float.Parse(oferta.salePrice, System.Globalization.CultureInfo.InvariantCulture) > precioMaximo)
                {
                    precioMaximo = float.Parse(oferta.salePrice, System.Globalization.CultureInfo.InvariantCulture);
                    //Console.WriteLine(precioMaximo.ToString());
                }
            }

            precioMinimo = precioMaximo;

            foreach (OfertaJuego oferta in listaOfertas)
            {
                if (float.Parse(oferta.salePrice, System.Globalization.CultureInfo.InvariantCulture) < precioMinimo)
                {
                    precioMinimo = float.Parse(oferta.salePrice, System.Globalization.CultureInfo.InvariantCulture);
                    //Console.WriteLine(precioMinimo.ToString());
                }
            }

            sldPrecio.Maximum = System.Math.Round(precioMaximo, 2);
            //sldPrecio.Minimum = System.Math.Round(precioMinimo, 2);
            sldPrecio.Minimum = 1;
            sldPrecio.Value = System.Math.Round(precioMaximo, 2);

            cbbValoraciones.Items.Add("Extremadamente positivas");
            cbbValoraciones.Items.Add("Muy positivas");
            cbbValoraciones.Items.Add("Positivas");
            cbbValoraciones.Items.Add("Mayormente positivas");
            cbbValoraciones.Items.Add("Variadas");
            cbbValoraciones.Items.Add("Mayormente negativas");

        }

        public static DateTime UnixTimeStampToDateTime(int unixTimeStamp)
        {
            DateTime dateTime = new DateTime(1970, 1, 1, 0, 0, 0, 0, DateTimeKind.Utc);
            dateTime = dateTime.AddSeconds(unixTimeStamp).ToLocalTime();
            return dateTime;
        }

        public Color colorValoracion(Label ratingTexto)
        {
            Color textColor = new Color();

            if (ratingTexto.Content != null)
            {
                if (ratingTexto.Content.Equals("Mostly Negative"))
                {
                    textColor = Colors.Red;
                    ratingTexto.Content = "Mayormente negativas";
                }
                else if (ratingTexto.Content.Equals("Mixed"))
                {
                    textColor = Colors.Yellow;
                    ratingTexto.Content = "Variadas";
                }
                else
                {
                    if (ratingTexto.Content.Equals("Positive"))
                    {
                        ratingTexto.Content = "Positivas";
                    }
                    else if (ratingTexto.Content.Equals("Mostly Positive"))
                    {
                        ratingTexto.Content = "Mayormente positivas";
                    }
                    else if (ratingTexto.Content.Equals("Very Positive"))
                    {
                        ratingTexto.Content = "Muy positivas";
                    }
                    else if (ratingTexto.Content.Equals("Overwhelmingly Positive"))
                    {
                        ratingTexto.Content = "Extremadamente positivas";
                    }
                    textColor = Color.FromArgb(255, 50, 173, 50);
                }
            }
            else
            {
                textColor = Colors.Gray;
                ratingTexto.Content = "n/a";
            }

            return textColor;
        }

        private void evtSliderPrecio(object sender, RoutedPropertyChangedEventArgs<double> e)
        {
            listaOfertasPorPrecio = new List<OfertaJuego>();
            contOferta = 0;
            numPagina = 1;
            lblPrecio.Content = "MENOS DE " + sldPrecio.Value + "€";

            foreach (OfertaJuego oferta in listaOfertas)
            {
                if (float.Parse(oferta.salePrice, System.Globalization.CultureInfo.InvariantCulture) <= sldPrecio.Value)
                {
                    listaOfertasPorPrecio.Add(oferta);
                }

            }
           
            insertarJuego(listaOfertasPorPrecio);

            if (sldPrecio.Value == sldPrecio.Maximum)
            {
                lblPrecio.Content = "CUALQUIER PRECIO";
            }
        }


        public void insertarJuego(List<OfertaJuego> listaOfertas)
        {
            Console.WriteLine(listaOfertas.Count());
            
            btnSiguiente.IsEnabled = true;
            btnAnterior.IsEnabled = true;


            Border border1 = new Border();
            border1.Background = new SolidColorBrush(Color.FromArgb(255, 63, 72, 81));
            border1.CornerRadius = new CornerRadius(5, 5, 5, 5);
            border1.Margin = new Thickness(10, 5, 10, 2.5);
            matrizPrincipal.Children.Add(border1);
            Grid.SetRow(border1, 0);
            Grid.SetColumnSpan(border1, 6);

            Border border2 = new Border();
            border2.Background = new SolidColorBrush(Color.FromArgb(255, 63, 72, 81));
            border2.CornerRadius = new CornerRadius(5, 5, 5, 5);
            border2.Margin = new Thickness(10, 2.5, 10, 2.5);
            matrizPrincipal.Children.Add(border2);
            Grid.SetRow(border2, 1);
            Grid.SetColumnSpan(border2, 6);

            Border border3 = new Border();
            border3.Background = new SolidColorBrush(Color.FromArgb(255, 63, 72, 81));
            border3.CornerRadius = new CornerRadius(5, 5, 5, 5);
            border3.Margin = new Thickness(10, 2.5, 10, 2.5);
            matrizPrincipal.Children.Add(border3);
            Grid.SetRow(border3, 2);
            Grid.SetColumnSpan(border3, 6);

            Border border4 = new Border();
            border4.Background = new SolidColorBrush(Color.FromArgb(255, 63, 72, 81));
            border4.CornerRadius = new CornerRadius(5, 5, 5, 5);
            border4.Margin = new Thickness(10, 2.5, 10, 2.5);
            matrizPrincipal.Children.Add(border4);
            Grid.SetRow(border4, 3);
            Grid.SetColumnSpan(border4, 6);

            Border border5 = new Border();
            border5.Background = new SolidColorBrush(Color.FromArgb(255, 63, 72, 81));
            border5.CornerRadius = new CornerRadius(5, 5, 5, 5);
            border5.Margin = new Thickness(10, 2.5, 10, 2.5);
            matrizPrincipal.Children.Add(border5);
            Grid.SetRow(border5, 4);
            Grid.SetColumnSpan(border5, 6);

            Border border6 = new Border();
            border6.Background = new SolidColorBrush(Color.FromArgb(255, 63, 72, 81));
            border6.CornerRadius = new CornerRadius(5, 5, 5, 5);
            border6.Margin = new Thickness(10, 2.5, 10, 5);
            matrizPrincipal.Children.Add(border6);
            Grid.SetRow(border6, 5);
            Grid.SetColumnSpan(border6, 6);

            totalPaginas = (int)Math.Ceiling((double)listaOfertas.Count / (double)6);

            tbNumResultados.Text = numPagina + "/" + totalPaginas;

            for (int fila = 0; fila <= 5; fila++)
            {
                for (int columna = 0; columna <= 5; columna++)
                {
                    if (contOferta < listaOfertasPorPrecio.Count)
                    {
                        switch (columna)
                        {
                            case 0:
                                Image imgJuego = new Image();
                                imgJuego.Source = (ImageSource)new ImageSourceConverter().ConvertFrom(new Uri(listaOfertas[contOferta].thumb));
                                imgJuego.Margin = new Thickness(15, 0, 0, 0);
                                imgJuego.HorizontalAlignment = HorizontalAlignment.Center;
                                imgJuego.VerticalAlignment = VerticalAlignment.Center;

                                matrizPrincipal.Children.Add(imgJuego);
                                Grid.SetRow(imgJuego, fila);
                                Grid.SetColumn(imgJuego, columna);
                                break;

                            case 1:
                                Label lblTitulo = new Label();
                                lblTitulo.Content = listaOfertas[contOferta].title;
                                lblTitulo.VerticalAlignment = VerticalAlignment.Center;
                                lblTitulo.Margin = new Thickness(20, 0, 0, 0);
                                lblTitulo.Foreground = new SolidColorBrush(Colors.WhiteSmoke);
                                lblTitulo.FontSize = 17;

                                matrizPrincipal.Children.Add(lblTitulo);
                                Grid.SetRow(lblTitulo, fila);
                                Grid.SetColumn(lblTitulo, columna);
                                break;

                            case 2:
                                Label lblFechaLanzamiento = new Label();
                                lblFechaLanzamiento.Content = UnixTimeStampToDateTime(listaOfertas[contOferta].releaseDate).ToShortDateString();
                                lblFechaLanzamiento.VerticalAlignment = VerticalAlignment.Center;
                                lblFechaLanzamiento.HorizontalAlignment = HorizontalAlignment.Center;
                                lblFechaLanzamiento.Foreground = new SolidColorBrush(Color.FromArgb(255, 153, 154, 158));

                                matrizPrincipal.Children.Add(lblFechaLanzamiento);
                                Grid.SetRow(lblFechaLanzamiento, fila);
                                Grid.SetColumn(lblFechaLanzamiento, columna);
                                break;

                            case 3:
                                StackPanel spRating = new StackPanel();
                                StackPanel spResenas = new StackPanel();
                                Label lblRatingTexto = new Label();
                                Label lblNumResenas = new Label();
                                Label lblResena = new Label();
                                Label lblRatingPorcentaje = new Label();

                                lblRatingTexto.Content = listaOfertas[contOferta].steamRatingText;
                                lblRatingTexto.Foreground = new SolidColorBrush(colorValoracion(lblRatingTexto));
                                lblRatingTexto.Margin = new Thickness(0, 0, -5, 0);

                                lblNumResenas.Content = listaOfertas[contOferta].steamRatingCount;

                                lblResena.Content = "| " + lblNumResenas.Content + " reseñas de usuarios";
                                lblResena.Foreground = new SolidColorBrush(Color.FromArgb(255, 107, 138, 170));

                                lblRatingPorcentaje.Content = "El " + listaOfertas[contOferta].steamRatingPercent + "% de las reseñas son positivas.";
                                lblRatingPorcentaje.Foreground = new SolidColorBrush(Color.FromArgb(255, 165, 176, 182));

                                spResenas.Orientation = Orientation.Horizontal;
                                spResenas.VerticalAlignment = VerticalAlignment.Center;
                                spRating.Background = new SolidColorBrush(Color.FromArgb(255, 61, 67, 75));
                                spResenas.Children.Add(lblRatingTexto);
                                spResenas.Children.Add(lblResena);

                                spRating.Children.Add(spResenas);
                                spRating.Children.Add(lblRatingPorcentaje);
                                spRating.VerticalAlignment = VerticalAlignment.Center;
                                spRating.Margin = new Thickness(20, 0, 0, 0);

                                matrizPrincipal.Children.Add(spRating);
                                Grid.SetRow(spRating, fila);
                                Grid.SetColumn(spRating, columna);
                                break;

                            case 4:
                                Label lblAhorro = new Label();
                                lblAhorro.Content = "-" + listaOfertas[contOferta].savings.Substring(0, listaOfertas[contOferta].savings.IndexOf(".")) + "%";
                                lblAhorro.VerticalAlignment = VerticalAlignment.Center;
                                lblAhorro.HorizontalAlignment = HorizontalAlignment.Center;
                                lblAhorro.FontSize = 18;
                                lblAhorro.FontWeight = FontWeights.Bold;
                                lblAhorro.Foreground = new SolidColorBrush(Colors.YellowGreen);
                                lblAhorro.Background = new SolidColorBrush(Colors.DarkGreen);

                                matrizPrincipal.Children.Add(lblAhorro);
                                Grid.SetRow(lblAhorro, fila);
                                Grid.SetColumn(lblAhorro, columna);
                                break;

                            case 5:
                                StackPanel spPrecio = new StackPanel();
                                TextBlock tbPrecioNormal = new TextBlock();
                                Label lblPrecioOferta = new Label();

                                tbPrecioNormal.Text = listaOfertas[contOferta].normalPrice + "€";
                                tbPrecioNormal.TextDecorations = TextDecorations.Strikethrough;
                                tbPrecioNormal.Margin = new Thickness(7, 0, 0, 0);
                                tbPrecioNormal.Foreground = new SolidColorBrush(Color.FromArgb(255, 110, 129, 142));

                                lblPrecioOferta.Content = listaOfertas[contOferta].salePrice + "€";
                                lblPrecioOferta.FontSize = 14;
                                lblPrecioOferta.Foreground = new SolidColorBrush(Colors.YellowGreen);

                                spPrecio.VerticalAlignment = VerticalAlignment.Center;
                                spPrecio.HorizontalAlignment = HorizontalAlignment.Center;
                                spPrecio.Children.Add(tbPrecioNormal);
                                spPrecio.Children.Add(lblPrecioOferta);

                                matrizPrincipal.Children.Add(spPrecio);
                                Grid.SetRow(spPrecio, fila);
                                Grid.SetColumn(spPrecio, columna);
                                break;

                            default: break;
                        }
                    }
                }
                contOferta++;

                if (contOferta >= listaOfertas.Count)
                {
                    btnSiguiente.IsEnabled = false;
                }

                if (contOferta <= 6)
                {
                    btnAnterior.IsEnabled = false;
                }
            }
        }


        private void evtSiguiente(object sender, RoutedEventArgs e)
        {
            numPagina++;
            insertarJuego(listaOfertasPorPrecio);
            Console.WriteLine("Num oferta: " + contOferta.ToString());
            Console.WriteLine("Total ofertas: " + listaOfertasPorPrecio.Count.ToString());
        }

        private void evtAnterior(object sender, RoutedEventArgs e)
        {
            if (listaOfertasPorPrecio.Count < 12)
            {
                contOferta = 0;
            }
            else
            {
                contOferta = contOferta - 12;
            }

            numPagina--;
            insertarJuego(listaOfertasPorPrecio);
        }

        private void btnBuscar_Click(object sender, RoutedEventArgs e)
        {
            listaOfertasPorPrecio.Clear();
            string titulo = tbTitulo.Text;
            Console.WriteLine(titulo);

            foreach (OfertaJuego oferta in listaOfertas)
            {
                if (oferta.title.ToLower().Contains(titulo) || oferta.title.Contains(titulo)
                    || oferta.title.ToUpper().Contains(titulo))
                {
                    listaOfertasPorPrecio.Add(oferta);
                    Console.WriteLine(oferta.title);
                }
            }
            contOferta = 0;
            numPagina = 1;

            insertarJuego(listaOfertasPorPrecio);
        }

        private void ComboBox_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            
            listaOfertasPorPrecio.Clear();

            foreach (OfertaJuego oferta in listaOfertas)
            {
                if (oferta.steamRatingText != null) {
                    if (cbbValoraciones.SelectedIndex == 0)
                    {                       
                        if (oferta.steamRatingText.Equals("Overwhelmingly Positive"))
                        {                          
                            listaOfertasPorPrecio.Add(oferta);
                        }

                    }
                    else if (cbbValoraciones.SelectedIndex == 1)
                    {
                        if (oferta.steamRatingText.Equals("Very Positive"))
                        {
                            listaOfertasPorPrecio.Add(oferta);
                        }
                    }

                    else if (cbbValoraciones.SelectedIndex == 2)
                    {
                        if (oferta.steamRatingText != null)
                        {
                            if (oferta.steamRatingText.Equals("Positive"))
                            {
                                listaOfertasPorPrecio.Add(oferta);

                            }
                        }
                    }

                    else if (cbbValoraciones.SelectedIndex == 3)
                    {
                        if (oferta.steamRatingText.Equals("Mostly Positive"))
                        {
                            listaOfertasPorPrecio.Add(oferta);
                        }
                    }

                    else if (cbbValoraciones.SelectedIndex == 4)
                    {
                        if (oferta.steamRatingText.Equals("Mixed"))
                        {
                            listaOfertasPorPrecio.Add(oferta);
                        }

                    }

                    else if (cbbValoraciones.SelectedIndex == 5)
                    {
                        if (oferta.steamRatingText.Equals("Mostly Negative"))
                        {
                            listaOfertasPorPrecio.Add(oferta);
                        }
                    }


                }
                
            }


            foreach (OfertaJuego oferta in listaOfertasPorPrecio)
            {
                Console.WriteLine(oferta.steamRatingText);
            }
            numPagina = 1;
            contOferta = 0;
            insertarJuego(listaOfertasPorPrecio);
        }
    }
}
