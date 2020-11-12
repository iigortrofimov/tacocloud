package com.trofimov.igor.tacos.repositories.jdbc.impl;

import com.trofimov.igor.tacos.domain.Ingredient;
import com.trofimov.igor.tacos.domain.Taco;
import com.trofimov.igor.tacos.repositories.jdbc.JdbcTacoRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Date;

@Repository
@AllArgsConstructor
public class JdbcTacoRepositoryImpl implements JdbcTacoRepository {

    private JdbcTemplate jdbc;

    @Override
    public Taco save(Taco taco) {
        long tacoId = saveTacoInfo(taco);
        taco.setId(tacoId);
        for (Ingredient ingredient : taco.getIngredients()) {
            saveIngredientToTaco(ingredient.getId(), tacoId);
        }
        return taco;
    }

/*    private long saveTacoInfo(Taco taco) {
        taco.setCreatedAt(new Date());
        PreparedStatementCreator psc =
                new PreparedStatementCreatorFactory(
                        "insert into Taco (name, createdAt) values (?, ?)",
                        Types.VARCHAR, Types.TIMESTAMP)
                        .newPreparedStatementCreator(
                                Arrays.asList(
                                        taco.getName(),
                                        new Timestamp(taco.getCreatedAt().getTime())));
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(psc, keyHolder);
        return keyHolder.getKey().longValue();
    }*/

    private long saveTacoInfo(Taco taco) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        taco.setCreatedAt(new Date());
        jdbc.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement("insert into Taco (name, createdAt) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, taco.getName());
            ps.setDate(2, new java.sql.Date(taco.getCreatedAt().getTime()));
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }


    private void saveIngredientToTaco(String ingredientId, long tacoId) {
        jdbc.update(
                "insert into Taco_Ingredients (taco, ingredient) " +
                        "values (?, ?)",
                tacoId, ingredientId);
    }
}



