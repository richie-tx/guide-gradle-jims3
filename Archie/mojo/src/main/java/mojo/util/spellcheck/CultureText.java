package mojo.util.spellcheck;

import java.util.Hashtable;

public class CultureText
{

    public CultureText()
    {
    }

    public static synchronized String get(String s, int i)
    {
        if(firstRun || currentCultureMap != i)
            loadMap(i);
        String s1 = (String)map.get(s);
        if(s1 == null)
            return s;
        else
            return s1;
    }

    public static String getCultureText(String s)
    {
        return get(s, 10001);
    }

    static void loadMap(int i)
    {
        map = new Hashtable();
        if(i == 10002)
        {
            map.put("Ignore", "Ignorer");
            map.put("Ignore All", "Ignorer Tout");
            map.put("Change", "Remplacer");
            map.put("Change All", "Remp. Tout");
            map.put("Add", "Ajouter");
            map.put("Adding...", "s'ajouter..");
            map.put("Cancel", "Annuler");
            map.put("Finish", "Finition");
            map.put("Finished checking selection, would you like to check the rest of the text?", "Choix fini de v\351rification.  V\351rifiez le reste du texte?");
            map.put("Finished Checking Selection", "Choix fini de v\351rification.");
            map.put("The spelling check is complete.", "Fini.");
            map.put("No Spelling Errors In Text.", "Aucunes Erreurs.");
            map.put("Spelling", "Correcteur orthographique");
            map.put("Check Spelling", "Correcteur Orthographique");
            map.put("Checking Document...", "V\351rifiant Le Document...");
            map.put("Not in Dictionary:", "Non Trouv\351");
            map.put("In Dictionary:", "Trouv\351");
            map.put("Change To:", "Non Trouv\351");
            map.put("Resume", "Continuez");
            map.put("Suggestions:", "Suggestions:");
            map.put("Find Suggestions?", "Sugg\351rer?");
            map.put("Finding Suggestions...", "Recherche...");
            map.put("No Suggestions.", "Aucun");
            map.put("Spell checking document...", "Contr\364le orthographique le texte...");
        } else
        if(i == 10005)
        {
            map.put("Ignore", "Ignorar");
            map.put("Ignore All", "Ignorar Todas");
            map.put("Change", "Cambiar");
            map.put("Change All", "Cambiar Todos");
            map.put("Add", "Agregar");
            map.put("Adding...", "Adici\363n...");
            map.put("Cancel", "Cancelar");
            map.put("Finish", "Acabar");
            map.put("Finished checking selection, would you like to check the rest of the text?", "Selecci\363n acabada.  \277Verifique el resto del texto?");
            map.put("Finished Checking Selection", "Selecci\363n acabada.");
            map.put("The spelling check is complete.", "Acabado.");
            map.put("No Spelling Errors In Text.", "Ningunos Errores.");
            map.put("Spelling", "Corregir Ortograf\355a");
            map.put("Check Spelling", "Corregir Ortograf\355a");
            map.put("Checking Document...", "Comprobaci\363n Del Documento...");
            map.put("Not in Dictionary:", "Palabra mal escrita");
            map.put("In Dictionary:", "En Diccionario");
            map.put("Change To:", "Palabra mal escrita");
            map.put("Resume", "Contin\372e");
            map.put("Suggestions:", "Sugerencias:");
            map.put("Find Suggestions?", "\277Sugerencias?");
            map.put("Finding Suggestions...", "Encontrar sugerencias...");
            map.put("No Suggestions.", "Ningunas sugerencias");
            map.put("Spell checking document...", "Comprobaci\363n del deletreo el texto...");
        } else
        if(i == 10003)
        {
            map.put("Ignore", "Ignorieren");
            map.put("Ignore All", "Nie \344ndern");
            map.put("Change", "\304ndern");
            map.put("Change All", "Immer \344ndern");
            map.put("Add", "Hinzuf\374gen");
            map.put("Adding...", "Hinzuf\374gen...");
            map.put("Cancel", "Abbrechen");
            map.put("Finish", "Ende");
            map.put("Finished checking selection, would you like to check the rest of the text?", "Der vorgew\344hlte Text ist \374berpr\374ft worden. \334berpr\374fen Sie den Rest des Textes?");
            map.put("Finished Checking Selection", "Der vorgew\344hlte Text ist \374berpr\374ft worden.");
            map.put("The spelling check is complete.", "Komplett.");
            map.put("No Spelling Errors In Text.", "Keine Fehler.");
            map.put("Spelling", "Rechtschreib\374berpr\374fung");
            map.put("Check Spelling", "Rechtschreib\374berpr\374fung");
            map.put("Checking Document...", "\334berpr\374fung des Dokumentes...");
            map.put("Not in Dictionary:", "Nicht im W\366rterbuch:");
            map.put("In Dictionary:", "Im W\366rterbuch:");
            map.put("Change To:", "\304ndern In:");
            map.put("Resume", "Weiter");
            map.put("Suggestions:", "Vorschl\344ge:");
            map.put("Find Suggestions?", "EntdeckungcVorschl\344ge?");
            map.put("Finding Suggestions...", "EntdeckungcVorschl\344ge...");
            map.put("No Suggestions.", "Keine Vorschl\344ge");
            map.put("Spell checking document...", "Bann\374berpr\374fungstext...");
        } else
        if(i == 10004)
        {
            map.put("Ignore", "Ignora");
            map.put("Ignore All", "Ignora tutto ");
            map.put("Change", "Cambia in");
            map.put("Change All", "Cambia tutto");
            map.put("Add", "Aggiungi");
            map.put("Adding...", "Aggiunta...");
            map.put("Cancel", "Annullamento");
            map.put("Finish", "Terminato");
            map.put("Finished checking selection, would you like to check the rest of the text?", "Il testo selezionato \350 stato verificato.  Verifichi il resto del testo?");
            map.put("Finished Checking Selection", "Il testo selezionato \350 stato verificato.");
            map.put("The spelling check is complete.", "Completo.");
            map.put("No Spelling Errors In Text.", "Nessun errori nel testo.");
            map.put("Spelling", "Verificatore ortografico");
            map.put("Check Spelling", "Verificatore ortografico");
            map.put("Checking Document...", "Controllo ortografico...");
            map.put("Not in Dictionary:", "Cambia in:");
            map.put("In Dictionary:", "Approvazione:");
            map.put("Change To:", "Cambia in:");
            map.put("Resume", "Continui");
            map.put("Suggestions:", "Suggerimenti:");
            map.put("Find Suggestions?", "Suggerimenti?");
            map.put("Finding Suggestions...", "Ottenere i suggerimenti...");
            map.put("No Suggestions.", "Nessun suggerimenti");
            map.put("Spell checking document...", "Controllo ortografico...");
        } else
        if(i == 10007)
        {
            map.put("Ignore", "Ignore");
            map.put("Ignore All", "Ignore Tudo");
            map.put("Change", "Mude");
            map.put("Change All", "Mude Tudo");
            map.put("put", "Adicione");
            map.put("puting...", "Adi\347\343o...");
            map.put("Cancel", "Cancelamento");
            map.put("Finish", "Batente");
            map.put("Finished checking selection, would you like to check the rest of the text?", "A sele\347\343o verificando terminada, voc\352 gosta de verificar o descanso do texto?");
            map.put("Finished Checking Selection", "Sele\347\343o Verificando Terminada");
            map.put("The spelling check is complete.", "A verifica\347\343o de soletra\347\343o est\341 completa.");
            map.put("The Spelling Check Is Complete.", "A Verifica\347\343o De Soletra\347\343o Est\341 Completa.");
            map.put("No Spelling Errors In Text.", "Nenhuns Erros De Soletra\347\343o No Texto.");
            map.put("Spelling", "Soletra\347\343o");
            map.put("Check Spelling", "Verifique A Soletra\347\343o");
            map.put("Checking Document...", "Verificando O Original...");
            map.put("Not in Dictionary:", "N\343o no dicion\341rio:");
            map.put("Not In Dictionary:", "N\343o No Dicion\341rio:");
            map.put("In Dictionary:", "No Dicion\341rio:");
            map.put("Change To:", "Mudan\347a A:");
            map.put("Resume", "Resumo");
            map.put("Suggestions:", "Sugest\365es:");
            map.put("Find Suggestions?", "Sugest\365es Do Achado?");
            map.put("Finding Suggestions...", "Encontrando Sugest\365es...");
            map.put("No Suggestions.", "Nenhumas Sugest\365es.");
            map.put("Spell checking document...", "Original verificar de per\355odo...");
            map.put("Remove duplicate word", "Remova a palavra duplicada");
        } else
        {
            map.put("Ignore", "Ignore");
            map.put("Ignore All", "Ignore All");
            map.put("Change", "Change");
            map.put("Change All", "Change All");
            map.put("Add", "Add");
            map.put("Adding...", "Adding...");
            map.put("Cancel", "Cancel");
            map.put("Finish", "Finish");
            map.put("Finished checking selection, would you like to check the rest of the text?", "Finished checking selection, would you like to check the rest of the text?");
            map.put("Finished Checking Selection", "Finished Checking Selection");
            map.put("The spelling check is complete.", "The spelling check is complete.");
            map.put("No Spelling Errors In Text.", "No Spelling Errors In Text.");
            map.put("Spelling", "Spelling");
            map.put("Check Spelling", "Check Spelling");
            map.put("Checking Document...", "Checking Document...");
            map.put("Not in Dictionary:", "Not in Dictionary:");
            map.put("In Dictionary:", "In Dictionary:");
            map.put("Change To:", "Change To:");
            map.put("Resume", "Resume");
            map.put("Suggestions:", "Suggestions:");
            map.put("Find Suggestions?", "Find Suggestions?");
            map.put("Finding Suggestions...", "Finding Suggestions...");
            map.put("No Suggestions.", "No Suggestions.");
            map.put("Spell checking document...", "Spell checking document...");
        }
        currentCultureMap = i;
        firstRun = false;
    }

    static Hashtable map;
    static int currentCultureMap = 10001;
    static boolean firstRun = true;

}
