import org.sql2o.*;
import java.util.List;

public class Cuisine {
  private int mId;
  private String mType;

  public Cuisine (String type) {
    this.mType = type;
  }

  public int getId() {
    return mId;
  }

  public String getType() {
    return mType;
  }


  @Override
  public boolean equals(Object otherCuisine){
    if (!(otherCuisine instanceof Cuisine)) {
      return false;
    } else {
      Cuisine newCuisine = (Cuisine) otherCuisine;
      return this.getType().equals(newCuisine.getType()) &&
        this.getId() == newCuisine.getId();
    }
  }

  //CREATE
  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO cuisine(type) VALUES (:type)";
      this.mId = (int) con.createQuery(sql, true)
        .addParameter("type", this.mType)
        .executeUpdate()
        .getKey();
    }
  }

  //READ
  public static List<Cuisine> all() {
    String sql = "SELECT id AS mId, type AS mType FROM cuisine";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Cuisine.class);
    }
  }

  //FIND
  public static Cuisine find(int id) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT id AS mId, type AS mType FROM cuisine WHERE id=:id";
      Cuisine myCuisine = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Cuisine.class);
      return myCuisine;
    }
  }


  //UPDATE
  public void update(String newType) {
    this.mType = newType;
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE cuisine SET type = :newType WHERE id = :id";
      con.createQuery(sql)
        .addParameter("newType", newType)
        .addParameter("id", this.mId)
        .executeUpdate();
    }
  }

  //DELETE
  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM cuisine WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", this.mId)
        .executeUpdate();
    }
  }

  //GET RESTAURANTS
  public List<Restaurant> getRestaurants() {
    try(Connection con =DB.sql2o.open()) {
      String sql = "SELECT id AS mId, name AS mName, cuisine_id AS mCuisineId FROM restaurants WHERE cuisine_id=:id";
      return con.createQuery(sql)
        .addParameter("id", this.mId)
        .executeAndFetch(Restaurant.class);
    }
  }

}
