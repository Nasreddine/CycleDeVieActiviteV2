package edu.nasredine.cheniki.cycledevieactivite;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    /**
     * Exécuté chaque fois que l'utilisateur clique sur l'icône de l'application pour une première fois.
     * <p>
     * La fonction onCreate() est suivie d'un onStart().
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Attacher l'objet btnEnvoyer au bouton "btnEnvoyer" définit dans le fichie xml de l'interface
        Button btnEnvoyer = (Button) findViewById(R.id.btnEnvoyer);
        btnEnvoyer.setOnClickListener(btnEnvoyerOnClickListener);

    }


    /**
     * Quand l'utilisateur clique sur le bouton envoyer, on récupère le texte et on l'affiche
     * Voir l'implémentaiton de la méthode getTxtValeur() juste après pour voir comment elle est implémentée
     */

    View.OnClickListener btnEnvoyerOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            popUp("valeur saisie = " + getTxtValeur());
        }
    };


    /**
     * Cette méthode : attache d'abord un objet EtitText au composant graphique editTxtValeur
     * Puis elle renvoie la valeur de la zone texte du composant en appelant la méthode  getText()
     * @return String
     */

    public String getTxtValeur() {
        EditText zoneValeur = (EditText) findViewById(R.id.editTxtValeur);
        return zoneValeur.getText().toString();
    }

    /**
     * Quand Android met en pause cette activité,
     * on sauvgarde la valeur du  champ texte "editTxtValeur" dans un repertoire de préférences
     * L'objectif est de restaurer la valeur quand l'utilisateur revient sur cette activité, voir onStart() qui fait ça
     */
    @Override
    protected void onPause() {
        super.onPause();
        // ajouter la valeur du champ texte dans l'espace "cycle_vie_prefs"
        SharedPreferences settings = getSharedPreferences("cycle_vie_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("valeur", getTxtValeur());
        editor.commit();

    }

    /**
     * On restaure la valeur du champ texte quand l'utilisateur revient sur l'activité
     * la méthode setTxTValeur() remet la valeur stocké précédement après la récupérer de l'espace  "cycle_vie_prefs" par la méthode "settings.getString()"
     */
    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences settings = getSharedPreferences("cycle_vie_prefs", Context.MODE_PRIVATE);
        setTxTValeur(settings.getString("valeur", ""));


    }

    /**
     * attacher l'objet "zoneValeur" au composant "editTxtValeur", puis appeler "setText()" pour remettre la valeur
     * @param valeur
     */
    public void setTxTValeur(String valeur) {
        EditText zoneValeur = (EditText) findViewById(R.id.editTxtValeur);
        zoneValeur.setText(valeur);
    }




    // --------------------------------------------------
    View.OnClickListener btnQuitterOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };



    public void popUp(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
