function Country(name, abbrev) {
  this.name = name;
  this.abbrev = abbrev;
  this.countryCode = 0;
  this.phoneFormat = 0;
  this.phoneValidation = 0;
  this.zipCodeFormat = 0;
  this.zipCodeValidation = 0;
  this.territories = new Array();
  this.numTerritories = 0;
  this.addTerritory = addTerritory;
}

function Territory(name) {
  this.country = 0;
  this.name = name;
}

function getCountry(countryName) {
  var thisCountry = null;
  for (var i=0; i < numCountries; i++) {
    thisCountry = countryList[i];
    if (thisCountry.name == countryName)
      return thisCountry;
  }
  return null;
}

function addTerritory(territoryName) {
  var territory = new Territory(territoryName);
  this.territories[this.numTerritories++] = territory;
  territory.country = this;
}

function writeTerritories(select, countryName, selected) {
  select.options.length = 0;
  var thisTerritory = null;
  var thisCountry = getCountry(countryName);
  if (countryName == "Any")
  	select.options[0] = new Option("Any", "");
  else
  	select.options[0] = new Option("None", "");
  if (thisCountry != null) {
  	for (var i=0; i < thisCountry.numTerritories; i++) {
  	  thisTerritory = thisCountry.territories[i];
  	  select.options[i+1] = new Option(thisTerritory.name, thisTerritory.name);
  	  select.options[i+1].selected = (thisTerritory.name == selected);
  	}
  }
}

function writeCountries(select, selected) {
  select.options.length = 0;
  var thisCountry = null;
  select.options[0] = new Option("None", "");
  for (var i=0; i < numCountries; i++) {
    thisCountry = countryList[i];
    select.options[i+1] = new Option(thisCountry.name, thisCountry.name);
    select.options[i+1].selected = (thisCountry.name == selected);
  }
}



function writeCountryCodes(select, selected) {
  select.options.length = 0;
  var thisCountry = null;
  // create array of unique country codes
  var codeList = new Array();
  var noDuplicate = new String();
  var j = 0;
  for (var i=0; i<countryList.length; i++) {
     thisCountry = countryList[i];
     if (noDuplicate.indexOf(("*"+thisCountry.countryCode+"*")) == -1) {
        noDuplicate = noDuplicate + "*"+thisCountry.countryCode+"*";
        codeList[j++] = thisCountry.countryCode;
     }
  }

  // sort country codes
  codeList.sort();

  // write country codes
  select.options[0] = new Option("","");
  for (var i=0; i < codeList.length-1; i++) {
    select.options[i+1] = new Option(codeList[i],codeList[i]);
    select.options[i+1].selected = (codeList[i] == selected);
  }
}

function writeCountryCodesSearch(select) {
   select.options.length = 0;
   var thisCountry = null;
   // create array of unique country codes
   var codeList = new Array();
   var noDuplicate = new String();
   var j = 0;
   for (var i=0; i<countryList.length; i++) {
      thisCountry = countryList[i];
      if (noDuplicate.indexOf(("*"+thisCountry.countryCode+"*")) == -1) {
         noDuplicate = noDuplicate + "*"+thisCountry.countryCode+"*";
         codeList[j++] = thisCountry.countryCode;
      }
   }

   // sort country codes
   codeList.sort();

   // write country codes
   for (var i=0; i < codeList.length-1; i++) {
     select.options[i] = new Option(codeList[i],codeList[i]);
   }
}

function addCountry(countryName, abbrev, countryCode, phoneFormat, phoneValidation, zipCodeFormat, zipCodeValidation) {
  var country = new Country(countryName, abbrev);
  country.countryCode = countryCode;
  country.phoneFormat = phoneFormat;
  country.phoneValidation = phoneValidation;
  country.zipCodeFormat = zipCodeFormat;
  country.zipCodeValidation = zipCodeValidation;
  countryList[numCountries++] = country;
  return country;
}

function validatePhoneNumber(countryCode, phoneNumber) {
  if (phoneNumber.value == "") return null;
  if (countryCode.value == "") return "Must enter a country code";
  var code = countryCode.options[countryCode.selectedIndex].value;
  var thisCountry = null;
  for (var i=0; i < numCountries; i++) {
    thisCountry = countryList[i];
    if (thisCountry.countryCode == code) break;
  }
  var regexp = new RegExp(thisCountry.phoneValidation);
  if (!regexp.test(phoneNumber.value))
    return thisCountry.phoneFormat;
  else
    return null;
}

function validateZipCode(countryName, zipCode) {
  if (zipCode.value == "") return null;
  var thisCountry = getCountry(countryName.value);
  var regexp = new RegExp(thisCountry.zipCodeValidation);
  if (!regexp.test(zipCode.value))
    return thisCountry.zipCodeFormat;
  else
    return null;
}

function initializeCountries() {

var country = null;
//country = addCountry("FullName", "abreviation", "country code", "phone # format for user display", "phone # format regular expression for validation", "zip code format for user display", "zip code format regular expression for validation");
country = addCountry("Argentina", "ARG", "54", "##[##] #####[###]", "^\\(?\\d{2,4}\\)?[- .]?\\d{2,4}[- .]?\\d{5,8}$", "########", "^\\d{8}$");
country = addCountry("Australia", "AUS", "61", "# ########", "^\\d[\\W]?\\d{8}$", "AUS-####", "^(AUS-)?\\d{4}$");
country = addCountry("Austria", "AUT", "43", "#[###] ###[####]", "^\\d{1,4}[\\W]?\\d{3,7}$", "A-####", "^(A-)?\\d{4}$");
country = addCountry("Belgium", "BEL", "32", "#[#] ######[#]", "^\\d{1,2}[\\W]?\\d{6,7}$", "B-####", "^(B-)?\\d{4}$");
country = addCountry("Bermuda", "BMU", "441", "### #######", "^\\(?\\d{3}\\)?[- .]?\\d{3}[- .]?\\d{4}$", "?? [??]", "^[A-Za-z]{2}( [A-Za-z]{2})?$");
country = addCountry("Brazil", "BRA", "55", "##[#] ######[##]", "^\\(?\\d{2,3}\\)?[- .]?\\d{2,3}[- .]?\\d{6,8}$", "#####[-###]", "^\\d{5}(-\\d{3})?$");
country = addCountry("Canada", "CAN", "1", "### #######", "^\\(?\\d{3}\\)?[- .]?\\d{3}[- .]?\\d{4}$", "?#?  #?#", "^[A-Za-z]\\d[A-Za-z] \\d[A-Za-z]\\d$");
country.addTerritory("Alberta");
country.addTerritory("British Columbia");
country.addTerritory("Manitoba");
country.addTerritory("New Brunswick");
country.addTerritory("Newfoundland");
country.addTerritory("Northwest Territories");
country.addTerritory("Nova Scotia");
country.addTerritory("Nunavut");
country.addTerritory("Ontario");
country.addTerritory("Prince Edward Island");
country.addTerritory("Quebec");
country.addTerritory("Saskatchewan");
country.addTerritory("Yukon");

country = addCountry("China", "CHN", "86", "##[##] ####[####]", "^\\d{2,4}[\\W]?\\d{4,8}$", "######", "^\\d{6}$");
country = addCountry("Cyprus", "CYP", "357", "#[#] ####[##]", "^\\d{1,2}[\\W]?\\d{4,6}$", "", "^.*$");
country = addCountry("Denmark", "DNK", "45", "########", "^\\d{8}$", "DK-####", "^(DK-)?\\d{4}$");
country = addCountry("El Salvador", "SLV", "503", "#######", "^\\d{7}$", "#####[-####]", "^\\d{5}(-\\d{4})?$");
country = addCountry("Finland", "FIN", "358", "#[#] ####[#####]", "^\\d{1,2}[\\W]?\\d{4,9}$", "FIN-#####", "^(FIN-)?\\d{5}$");
country = addCountry("France", "FRA", "33", "##########", "^\\d{10}$", "#####", "^\\d{5}$");
country = addCountry("Germany", "DEU", "49", "##[###] ###[######]", "^\\d{2,5}[\\W]?\\d{3,9}$", "D-#####", "^(D-)?\\d{5}$");
country = addCountry("Greece", "GRC", "30", "#[##] #####[##]", "^\\d{1,3}[\\W]?\\d{5,7}$", "GR-### ##", "^(GR-)?\\d{3}\\W\\d{2}$");
country = addCountry("Greenland", "GRL", "299", "######", "^\\d{6}$", "DK-####", "^(DK-)?\\d{4}$");
country = addCountry("Honduras", "HND", "504", "#######", "^\\d{7}$", "#####[-####]", "^\\d{5}(-\\d{4})?$");
country = addCountry("Hong Kong", "HKG", "852", "########", "^\\d{8}$", "", "");
country = addCountry("Hungary", "HUN", "36", "#[#] ######[#]", "^\\d{1,2}[\\W]?\\d{6,7}$", "H-####", "^(H-)?\\d{4}");
country = addCountry("Iceland", "ISL", "354", "#######", "^\\d{7}$", "??-###", "^[A-Za-z]{2}-\\d{3}$");
country = addCountry("India", "IND", "91", "##[#####] ####[###]", "^\\d{2,7}[\\W]?\\d{4,7}$", "### ###", "^\\d{3}\\W\\d{3}$");
country = addCountry("Ireland", "IRL", "353", "#[##] #####[##]", "^\\d{1,3}[\\W]?\\d{5,7}$", "", "^.*$");
country = addCountry("Israel", "ISR", "972", "#[#] ######[#]", "^\\d{1,2}[\\W]?\\d{6,7}$", "ISL-#####", "^(ISL-)?\\d{5}$");
country = addCountry("Italy", "ITA", "39", "#[##] #####[###]", "^\\d{1,3}[\\W]?\\d{5,8}$", "I-#####", "^(I-)?\\d{5}$");
country = addCountry("Japan", "JPN", "81", "#[##] ######[##]", "^\\d{1,3}[\\W]?\\d{6,8}$", "###", "^\\d{3}$");
country = addCountry("Luxembourg", "LUX", "352", "#####[#]", "^\\d{5,6}$", "L-####", "^(L-)?\\d{4}");
country = addCountry("Mexico", "MEX", "52", "#[##] #####[##]", "^\\d{1,3}[\\W]?\\d{5,7}$", "#####", "^\\d{5}$");
country.addTerritory("Aguascalientes");
country.addTerritory("Baja California");
country.addTerritory("Baja California Sur");
country.addTerritory("Campeche");
country.addTerritory("Chiapas");
country.addTerritory("Chihuahua");
country.addTerritory("Coahuila");
country.addTerritory("Colima");
country.addTerritory("Distrito Federal");
country.addTerritory("Durango");
country.addTerritory("Guanajuato");
country.addTerritory("Guerrero");
country.addTerritory("Hidalgo");
country.addTerritory("Jalisco");
country.addTerritory("Mexico");
country.addTerritory("Michoacan de Ocampo");
country.addTerritory("Morelos");
country.addTerritory("Nayarit");
country.addTerritory("Nuevo Leon");
country.addTerritory("Oaxaca");
country.addTerritory("Puebla");
country.addTerritory("Queretaro");
country.addTerritory("Quintana Roo");
country.addTerritory("San Luis Potosi");
country.addTerritory("Sinaloa");
country.addTerritory("Sonora");
country.addTerritory("Tabasco");
country.addTerritory("Tamaulipas");
country.addTerritory("Tlaxcala");
country.addTerritory("Veracruz-Llave");
country.addTerritory("Yucatan");
country.addTerritory("Zacatecas");
country = addCountry("Netherlands", "NLD", "31", "##[#] ######[#]", "^\\d{2,3}[\\W]?\\d{6,7}$", "NL-####", "^(NL-)?\\d{4}$");
country = addCountry("New Zealand", "NZL", "64", "# #######", "^\\d{1}[\\W]?\\d{7}$", "####", "^\\d{4}$");
country = addCountry("Norway", "NOR", "47", "########", "^\\d{8}$", "N-####", "^(N-)?\\d{4}$");
country = addCountry("Panama", "PAN", "507", "#######", "^\\d{7}$", "#####[-####]", "^\\d{5}(-\\d{4})?$");
country = addCountry("Poland", "POL", "48", "#[###] ####[###]", "^\\d{1,4}[\\W]?\\d{4,7}$", "##-###", "^\\d{2}-\\d{3}$");
country = addCountry("Portugal", "PRT", "351", "#[#] #####[##]", "^\\d{1,2}[\\W]?\\d{5,7}$", "P-####", "^(P-)?\\d{4}$");
country = addCountry("Singapore", "SGP", "65", "#######[#]", "^\\d{7,8}$", "######", "^\\d{6}$");
country = addCountry("South Africa", "ZAF", "27", "##[###] ###[####]", "^\\d{2,5}[\\W]?\\d{3,7}$", "####", "^\\d{4}$");
country = addCountry("South Korea", "PRK", "82", "#[#] ######[#]", "^\\d{1,2}[\\W]?\\d{6,7}$", "###-###", "^\\d{3}-\\d{3}$");
country = addCountry("Spain", "ESP", "34", "#########", "^\\d{9}$", "E-#####", "^(E-)?\\d{5}$");
country = addCountry("Sweden", "SWE", "46", "#[##] #####[###]", "^\\d{1,3}[\\W]?\\d{5,8}$", "SE-### ##", "^(SE-)?\\d{3}\\W\\d{2}$");
country = addCountry("Switzerland", "CHE", "41", "#[#] #####[##]", "^\\d{1,2}[\\W]?\\d{5,7}$", "####", "^\\d{4}$");
country = addCountry("Taiwan, Province of China", "TWN", "886", "#[#] ######[##]", "^\\d{1,2}[\\W]?\\d{6,8}$", "###-##", "^\\d{3}-\\d{2}$");
country = addCountry("Turkey", "TUR", "90", "### #######", "^\\d{3}[\\W]?\\d{7}$", "??-#####", "^[A-Za-z]{2}-\\d{5}$");
country = addCountry("United Kingdom", "UK", "44", "##[####] ###[#####]", "^\\d{2,6}[\\W]?\\d{3,8}$", "??#?  #??", "\\w\\w\\d\\w \\d\\w\\w");
country = addCountry("United States", "USA", "1", "### #######", "^\\(?\\d{3}\\)?[- .]?\\d{3}[- .]?\\d{4}$", "#####[-####]", "^\\d{5}(-\\d{4})?$");
country.addTerritory("Alabama");
country.addTerritory("Alaska");
country.addTerritory("American Samoa");
country.addTerritory("Arizona");
country.addTerritory("Arkansas");
country.addTerritory("California");
country.addTerritory("Colorado");
country.addTerritory("Connecticut");
country.addTerritory("Delaware");
country.addTerritory("District of Columbia");
country.addTerritory("Florida");
country.addTerritory("Georgia");
country.addTerritory("Guam");
country.addTerritory("Hawaii");
country.addTerritory("Idaho");
country.addTerritory("Illinois");
country.addTerritory("Indiana");
country.addTerritory("Iowa");
country.addTerritory("Kansas");
country.addTerritory("Kentucky");
country.addTerritory("Louisiana");
country.addTerritory("Maine");
country.addTerritory("Maryland");
country.addTerritory("Massachusetts");
country.addTerritory("Michigan");
country.addTerritory("Minnesota");
country.addTerritory("Mississippi");
country.addTerritory("Missouri");
country.addTerritory("Montana");
country.addTerritory("Nebraska");
country.addTerritory("Nevada");
country.addTerritory("New Hampshire");
country.addTerritory("New Jersey");
country.addTerritory("New Mexico");
country.addTerritory("New York");
country.addTerritory("North Carolina");
country.addTerritory("North Dakota");
country.addTerritory("Ohio");
country.addTerritory("Oklahoma");
country.addTerritory("Oregon");
country.addTerritory("Pennsylvania");
country.addTerritory("Puerto Rico");
country.addTerritory("Rhode Island");
country.addTerritory("South Carolina");
country.addTerritory("South Dakota");
country.addTerritory("Tennessee");
country.addTerritory("Texas");
country.addTerritory("Utah");
country.addTerritory("Vermont");
country.addTerritory("Virgin Islands");
country.addTerritory("Virginia");
country.addTerritory("Washington");
country.addTerritory("West Virginia");
country.addTerritory("Wisconsin");
country.addTerritory("Wyoming");

country = addCountry("Vatican City State", "VAT", "39", "", "^.*$", "?-#####", "\\w-\\d{5}");

}


var countryList = new Array();
var numCountries = 0;

initializeCountries();