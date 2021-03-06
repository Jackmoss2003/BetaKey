/*
 *  Developed by HyplexMC.
 *  Last modified 21.04.22.
 *  Copyright (c) 2022.
 */

package org.hyplexmc.betakey.database.querys;

import org.hyplexmc.betakey.BetaKey;
import org.hyplexmc.betakey.database.DBManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class BetaPlayer {

    private final DBManager dbManager = BetaKey.instance.getDbManager();
    private final String uuid;
    private final BetaPlayerInfo betaPlayerInfo;
    private boolean valid;

    public BetaPlayer(UUID uuid) {
        this.uuid = uuid.toString().replace("-", "");
        this.betaPlayerInfo = BetaPlayerInfo.getPlayerInfo(uuid.toString().replace("-", ""));
    }


    public void create(String betakey) {
        if (!dbManager.isConnected()) System.err.println("Not connected to any DB");
        if (betaPlayerInfo != null) {
            System.err.println("BetaKey >> Player already exists");
            return;
        }

        try (PreparedStatement preparedStatement = dbManager.getConnection().prepareStatement("INSERT INTO betaplayer(UUID, BETAKEY) VALUES (?, ?)")) {

            preparedStatement.setString(1, uuid);
            preparedStatement.setString(2, betakey);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("BetaKey >> Player created");

    }

    public void remove() {
        if (!dbManager.isConnected()) System.err.println("Not connected to any DB");
        if (betaPlayerInfo == null) {
            System.err.println("BetaKey >> Player does not exists");
            return;
        }

        try (PreparedStatement preparedStatement = dbManager.getConnection().prepareStatement("DELETE FROM betaplayer WHERE UUID=?")) {

            preparedStatement.setString(1, uuid);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("BetaKey >> Player removed");
    }

    public BetaPlayerInfo getBetaPlayerInfo() {
        return betaPlayerInfo;
    }

    public boolean isValid() {
        if (this.betaPlayerInfo != null) {
            this.valid = true;
        }
        return valid;
    }
}
