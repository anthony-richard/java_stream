import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Principale {
    public static void main(String[] args) {
        Etudiant etudiant1 = new Etudiant("bansept", "franck");
        etudiant1.getListeNote().add(new Note("JAVA", 15));
        etudiant1.getListeNote().add(new Note("PHP", 10));
        etudiant1.getListeNote().add(new Note("UML", 5));

        Etudiant etudiant2 = new Etudiant("doe", "simon");
        etudiant2.getListeNote().add(new Note("JAVA", 5));
        etudiant2.getListeNote().add(new Note("PHP", 19));

        Etudiant etudiant3 = new Etudiant("stark", "sansa");
        etudiant3.getListeNote().add(new Note("C#", 5));
        etudiant3.getListeNote().add(new Note("AVA", 19));


        List<Etudiant> listeEtudiant = new ArrayList<>();
        listeEtudiant.add(etudiant1);
        listeEtudiant.add(etudiant2);
        listeEtudiant.add(etudiant3);

        //exo 1

        System.out.println(
                listeEtudiant.stream().
                        map(personne -> personne.getNom() + "." + personne.getPrenom() + "@cesi.com")
                        .collect(Collectors.joining(" - ")));


        // exo 2
        // liste prenom
        Stream<Etudiant> prenom= listeEtudiant.stream()
                .filter(s -> s.getPrenom().startsWith("s") && s.getNom().startsWith("s"));

        prenom.forEach(s -> System.out.println(s.getPrenom()));

        // liste nom
        Stream<Etudiant> nom= listeEtudiant.stream()
                .filter(s -> s.getNom().startsWith("s"));

        nom.forEach(s -> System.out.println(s.getNom()));

        // nom et prenom de la personne avec note la plus élevée
        System.out.println(
                listeEtudiant.stream()
                        .filter(etudiant -> etudiant.getPrenom().startsWith("s"))
                        .sorted(Comparator.comparing(etudiant -> etudiant.getListeNote().stream()
                                .mapToInt(note -> note.getNote()).max().getAsInt()))
                        .map( etudiant -> etudiant.getNom() + ' ' + etudiant.getPrenom())
                        .findFirst().get());

        //exo 3
        ArrayList<Evaluation> list = new ArrayList<>();
        listeEtudiant.stream()
                .forEach((etudiant -> etudiant.getListeNote().stream()
                .forEach((note -> list.add(new Evaluation(etudiant, note.getNote()))))
        ));
        System.out.println(list);

        //exo4
        System.out.println(
                listeEtudiant.stream()
                        .sorted(Comparator.comparing(personne -> personne.getListeNote().stream()
                                .mapToInt(note -> note.getNote()).average().getAsDouble()))
                        .map(personne ->
                                personne.nom.charAt(0) + "." +
                                        personne.prenom.charAt(0) + " (" +
                                        personne.getListeNote().stream()
                                                .mapToInt(note -> note.getNote()).max().getAsInt() + ")"
                        )
                        .collect(Collectors.joining(" > ")));
    }
}
