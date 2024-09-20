package com.example.lab1;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//creation de la class Cours
    class Cours {
        private int numero;
        private String titre;
        private String session;
        private String professeur;
        private double note;

        public Cours(int numero, String titre, String session ,String professeur,double note){
            this.numero = numero;
            this.titre = titre;
            this.session = session;
            this.professeur = professeur;
            this.note = note;
        }
        //getteurs et setteurs
        public int getNumero() {
            return numero;
        }
        public void setNumero(int numero) {
            this.numero = numero;
        }

        public String getTitre() {
            return titre;
        }
        public void setTitre(String titre) {
            this.titre = titre;
        }
        public String getSession() {
            return session;
        }
        public void setSession(String session) {
            this.session = session;
        }
        public String getProfesseur() {
            return professeur;
        }
        public void setProfesseur(String professeur) {
            this.professeur = professeur;
        }
        public double getNote() {
            return note;
        }
        public void setNote(double note) {
            this.note = note;
        }
        // Méthode pour calculer la moyenne académique


    }
public class MainActivity extends AppCompatActivity {

    private EditText editNumero, editTitre, editSession, editProfesseur, editNote, editRecherche ;
    private TextView textResult, textMoyenne, textshow ;
    private Button buttonajtmy, btnsession,btnnumero,btnprof,btnmax;

    private ArrayList<Cours> coursList = new ArrayList<>();
    private ArrayAdapter<Cours> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNumero = findViewById(R.id.editNumero);
        editTitre = findViewById(R.id.editTitre);
        editSession = findViewById(R.id.editSession);
        editProfesseur = findViewById(R.id.editProfesseur);
        editNote = findViewById(R.id.editNote);
        editRecherche = findViewById(R.id.editRecherche);

        textResult = findViewById(R.id.textResult);
        textMoyenne = findViewById(R.id.textMoyenne);
        textshow = findViewById(R.id.textshow);

        Button buttonajtmy = findViewById(R.id.buttonajtmy);
        Button btnsession = findViewById(R.id.btnsession);
        Button btnnumero = findViewById(R.id.btnnumero);
        Button btnprof = findViewById(R.id.btnprof);
        Button btnmax = findViewById(R.id.btnmax);


        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, coursList);


        buttonajtmy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ajouterCours();
            }
            private void ajouterCours() {
                try {
                int numero = Integer.parseInt(editNumero.getText().toString());
                String titre = editTitre.getText().toString();
                String session = editSession.getText().toString();
                String professeur = editProfesseur.getText().toString();
                double note = Double.parseDouble(editNote.getText().toString());
                Cours cours = new Cours(numero, titre, session, professeur, note);
                coursList.add(cours);

                // Mettre à jour la moyenne académique
                double moyenne = calculerMoyenne();
                textMoyenne.setText("La Moyenne académique = " + moyenne);
                textResult.setText("Total cours :"+ coursList.stream().count());

                // Réinitialiser les champs du formulaire
                editNumero.setText("");
                editTitre.setText("");
                editSession.setText("");
                editProfesseur.setText("");
                editNote.setText("");
                } catch (NumberFormatException e) {
                    // Gérer l'exception si la conversion échoue
                    e.printStackTrace();
                }
            }

            public double calculerMoyenne() {
                double sommeNotes = 0;
                for (Cours cours : coursList) {
                    sommeNotes += cours.getNote();
                }
                return sommeNotes / coursList.size();
            }
        });

        btnsession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RParSession();
            }
        private void RParSession() {
            try {
            String sessionRecherche = editRecherche.getText().toString();
            StringBuilder parSession = new StringBuilder("Recherche par rapport a la session :\n");
            for (Cours cours : coursList) {
                if (String.valueOf(cours.getSession()).equals(sessionRecherche)) {
                    parSession.append("Numéro : "+cours.getNumero()).append("\n").append("Titre : "+cours.getTitre()).append(" \n ").append("Professeur : "+cours.getProfesseur()).append(" \n ").append("Note : "+cours.getNote());

                }
            }
            textshow.setText(parSession.toString());
            } catch (NumberFormatException e) {
                // Gérer les erreurs de format de saisie
                e.printStackTrace();
            }
        }
            });
        btnnumero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RParNumero();
            }
            private void RParNumero() {
                try {
                String numeroRecherche = editRecherche.getText().toString();
                StringBuilder parNumero = new StringBuilder("Recherche par rapport au numéro de cour :\n");
                for (Cours cours : coursList) {
                    if (String.valueOf(cours.getNumero()).equals(numeroRecherche)) {
                        parNumero.append("Titre : "+cours.getTitre()).append(" \n ").append("Session : "+cours.getSession()).append("\n").append("Professeur : "+cours.getProfesseur()).append(" \n ").append("Note : "+cours.getNote());
                    }
                }
                textshow.setText(parNumero.toString());
                } catch (NumberFormatException e) {
                    // Gérer les erreurs de format de saisie
                    e.printStackTrace();
                }
            }
        });
        btnprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RParProfesseur();
            }
            private void RParProfesseur() {
                String professeurRecherche = editRecherche.getText().toString();
                StringBuilder parProf = new StringBuilder("Recherche par rapport au nom du professeur  :\n");
                for (Cours cours : coursList) {
                    if (String.valueOf(cours.getProfesseur()).equals(professeurRecherche)) {
                        parProf.append("Numéro : "+cours.getNumero()).append("\n").append("Titre : "+cours.getTitre()).append(" \n ").append("Session : "+cours.getSession()).append("\n").append("Note : "+cours.getNote());
                    }
                }
                textshow.setText(parProf.toString());
            }
        });

        btnmax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MeuilleursNotes();
            }
        private void MeuilleursNotes() {
            List<Cours> meilleursCours = new ArrayList<>(coursList);

            // Trier pour avoir les trois meilleurs noites du cours
            for (int i = 0; i < meilleursCours.size() - 1; i++) {
                for (int j = i + 1; j < meilleursCours.size(); j++) {
                    if (meilleursCours.get(i).getNote() < meilleursCours.get(j).getNote()) {

                        Cours temp = meilleursCours.get(i);
                        meilleursCours.set(i, meilleursCours.get(j));
                        meilleursCours.set(j, temp);
                    }
                }
            }

            StringBuilder meilleursCourstxt = new StringBuilder("Les trois meuilleurs notes sont :\n");
            int maxCoursesToShow = Math.min(3, coursList.size());
            for (int i = 0; i < maxCoursesToShow; i++) {
                Cours cours = coursList.get(i);
                meilleursCourstxt.append(cours.getTitre()).append(" - ").append(cours.getNote()).append("\n");
            }
            textshow.setText(meilleursCourstxt.toString());
        }
    });
    }
    }



