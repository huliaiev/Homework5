package com.gmail.ahuliaiev;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.Scanner;

public class Main {

    static EntityManagerFactory emf;
    static EntityManager em;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {

            emf = Persistence.createEntityManagerFactory("JPATest");
            em = emf.createEntityManager();
            try {
                while (true) {
                    System.out.println("1: add dish");
                    System.out.println("2: delete dish");
                    System.out.println("3: view all dishes");
                    System.out.println("4: view dishes cost 'OT --> DO' ");
                    System.out.println("5: tolko so skidkoy");
                    System.out.println("6: blyuda s vesom ne bolee 1 kg");
                    System.out.print("-> ");

                    String s = sc.nextLine();
                    switch (s) {
                        case "1":
                            addDish(sc);
                            break;
                        case "2":
                            deleteDish(sc);
                            break;
                        case "3":
                            viewAllDishes();
                            break;
                        case "4":
                            viewAllDishesByCost();
                            break;
                        case "5":
                            viewOnlyDiscount();
                            break;
                        case "6":
                            viewWeightSum();
                            break;
                        default:
                            return;
                    }
                }
            } finally {
                sc.close();
                em.close();
                emf.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
    }

    private static void addDish(Scanner sc) {
        System.out.print("Enter dish name: ");
        String dishName = sc.nextLine();

        System.out.print("Enter dish cost: ");
        String sdishCost = sc.nextLine();
        Double dishCost = Double.parseDouble(sdishCost);

        System.out.print("Enter dish weight: ");
        String sdishWeight = sc.nextLine();
        Double dishWeight = Double.parseDouble(sdishWeight);


        System.out.println("Discount? Y/N:");
        String sdiscount = sc.nextLine();

        boolean discount = false;
        String s2 = "Y";
        String s3 = "N";
        if (sdiscount.equalsIgnoreCase(s2)) {
            discount = true;
        }
        if (sdiscount.equalsIgnoreCase(s3)) {
            discount = false;
        }

        em.getTransaction().begin();
        try {
            MenuRestaraunt mr = new MenuRestaraunt(dishName, dishCost, dishWeight, discount);
            em.persist(mr);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    private static void deleteDish(Scanner sc) {
        System.out.print("Enter dish id: ");
        String sId = sc.nextLine();
        long id = Long.parseLong(sId);

        MenuRestaraunt mr = em.find(MenuRestaraunt.class, id);
        if (mr == null) {
            System.out.println("Dish not found!");
            return;
        }

        em.getTransaction().begin();
        try {
            em.remove(mr);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    private static void viewAllDishes() {
        Query query = em.createQuery("SELECT mr FROM MenuRestaraunt mr", MenuRestaraunt.class);
        List<MenuRestaraunt> list = (List<MenuRestaraunt>) query.getResultList();

        for (MenuRestaraunt mr1 : list)
            System.out.println(mr1);
    }

    //Вывести по стоимости от и до
    private static void viewAllDishesByCost() {
        Query query = em.createQuery("SELECT mr FROM MenuRestaraunt mr  order by dishCost ", MenuRestaraunt.class);

        List<MenuRestaraunt> list = (List<MenuRestaraunt>) query.getResultList();

        for (MenuRestaraunt mr1 : list)
            System.out.println(mr1);
    }

    //Вывести только со скидкой
    private static void viewOnlyDiscount() {
        Query query = em.createQuery("SELECT mr FROM MenuRestaraunt mr WHERE discount = true ");

        List<MenuRestaraunt> list = (List<MenuRestaraunt>) query.getResultList();

        for (MenuRestaraunt mr1 : list)
            System.out.println(mr1);
    }

    //Суммарный вес не более 1 кг
    private static void viewWeightSum() {
        Query query = em.createQuery("SELECT mr FROM MenuRestaraunt mr WHERE  dishWeight <= 1 ");
        List<MenuRestaraunt> list = (List<MenuRestaraunt>) query.getResultList();

        for (MenuRestaraunt mr1 : list)
            System.out.println(mr1);
    }
}



