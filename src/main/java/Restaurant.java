import org.sql2o.*;
import java.util.List;

public class Restaurant {
  private int mId;
  private String mName;
  private int mCuisineId;

  public Restaurant (String name, int cuisineId) {
    this.mName = name;
    this.mCuisineId = cuisineId;
  }

  public int getId() {
    return mId;
  }

  public String getName() {
    return mName;
  }

  public int getCuisineId() {
    return mCuisineId;
  }

  @Override
  public boolean equals(Object otherRestaurant){
    if (!(otherRestaurant instanceof Restaurant)) {
      return false;
    } else {
      Restaurant newRestaurant = (Restaurant) otherRestaurant;
      return this.getName().equals(newRestaurant.getName()) &&
             this.getId() == newRestaurant.getId() &&
             this.getCuisineId() == newRestaurant.getCuisineId();
    }
  }
  //CREATE
  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO restaurants (name, cuisine_id) VALUES (:name, :cuisine_id)";
      this.mId = (int) con.createQuery(sql, true)
        .addParameter("name", this.mName)
        .addParameter("cuisine_id", this.mCuisineId)
        .executeUpdate()
        .getKey();
    }
  }

  // //READ
  // public static List<Cuisine> all() {
  //   String sql = "SELECT id AS mId, type AS mType FROM cuisine";
  //   try (Connection con = DB.sql2o.open()) {
  //     return con.createQuery(sql).executeAndFetch(Cuisine.class);
  //   }
  // }

  // //FIND
  // public static Cuisine find(int id) {
  //   try (Connection con = DB.sql2o.open()) {
  //     String sql = "SELECT id AS mId, type AS mType FROM cuisine WHERE id=:id";
  //     Cuisine myCuisine = con.createQuery(sql)
  //       .addParameter("id", id)
  //       .executeAndFetchFirst(Cuisine.class);
  //     return myCuisine;
  //   }
  // }
  //
  //
  // //UPDATE
  // public void update(String newType) {
  //   this.mType = newType;
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "UPDATE cuisine SET type = :newType WHERE id = :id";
  //     con.createQuery(sql)
  //       .addParameter("newType", newType)
  //       .addParameter("id", this.mId)
  //       .executeUpdate();
  //   }
  // }
  //
  // //DELETE
  // public void delete() {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "DELETE FROM cuisine WHERE id = :id";
  //     con.createQuery(sql)
  //       .addParameter("id", this.mId)
  //       .executeUpdate();
  //   }
  // }

//   /******************************************************
//     Students:
//     TODO: Create method to get cuisine type
//   *******************************************************/

}
