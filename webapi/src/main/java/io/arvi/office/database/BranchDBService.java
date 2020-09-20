package io.arvi.office.database;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.Row;
import io.vertx.sqlclient.RowSet;

public class BranchDBService {

    private PgPool dbClient;

    public BranchDBService(Vertx vertx, PgPool client) {
        this.dbClient = client;

        vertx.eventBus().consumer("database.branches", this::onMessage);
    }

    private void onMessage(Message<JsonObject> message) {
        String action = message.headers().get("action");
    
        switch (action) {
            case "get-branches":
                getBranches(message);
                break;
            case "create-branch":
                createBranch(message);
                break;
            case "update-branch":
                updateBranch(message);
                break;
            default:
                break;
        }
    }

    private void getBranches(Message<JsonObject> message) {
        dbClient.query("SELECT * FROM public.branches WHERE company_id = 1")
            .execute(ar -> {
                if (ar.succeeded()) {
                    RowSet<Row> results = ar.result();
                    if (results.size() == 0) {
                        JsonArray branches = new JsonArray();
                        message.reply(branches);
                    } else {
                        JsonArray branches = new JsonArray();
                        for (Row row : results) {
                            JsonObject branch = new JsonObject();
                            branch
                                .put("branch_id", row.getInteger("branch_id"))
                                .put("company_id", row.getInteger("company_id"))
                                .put("name", row.getString("name"))
                                .put("address", row.getString("address"))
                                .put("city", row.getString("city"))
                                .put("district", row.getString("district"))
                                .put("phone", row.getString("phone"))
                                .put("active", row.getBoolean("active"))
                                .put("version", row.getInteger("version"))
                                .put("created_date", row.getOffsetDateTime("created_date").toInstant())
                                .put("created_by", row.getString("created_by"))
                                .put("updated_date", row.getOffsetDateTime("updated_date").toInstant())
                                .put("updated_by", row.getString("updated_by"))
                                .put("deleted", row.getBoolean("deleted"));

                            branches.add(branch);
                        }
                        message.reply(branches);
                    }                    
                } else {
                    System.out.println("Query Failed");
                }
            });
    }

    private void createBranch(Message<JsonObject> message) {

    }

    private void updateBranch(Message<JsonObject> message) {

    }
}