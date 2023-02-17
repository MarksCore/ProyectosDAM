using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PruebaAPI
{
    public class OfertaJuego
    {
        public String internalName { get; set; }
        public String title { get; set; }
        public String metacriticLink { get; set; }
        public String dealID { get; set; }
        public String storeID { get; set; }
        public String gameID { get; set; }
        public String salePrice { get; set; }
        public String normalPrice { get; set;}
        public String isOnSale { get; set; }
        public String savings { get; set; }
        public String metacriticScore { get; set; }
        public String steamRatingText { get; set; }
        public String steamRatingPercent { get; set; }
        public String steamRatingCount { get; set; }
        public String steamAppID { get; set; }
        public int releaseDate { get; set; }
        public int lastChange { get; set; }
        public String thumb { get; set; }
    }
}
