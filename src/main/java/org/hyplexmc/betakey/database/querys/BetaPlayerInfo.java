/*
 *  Developed by HyplexMC.
 *  Last modified 21.04.22.
 *  Copyright (c) 2022.
 */
package org.hyplexmc.betakey.database.querys;

import org.hyplexmc.betakey.BetaKey;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BetaPlayerInfo {

    private final String betaKey;

    private BetaPlayerInfo(ResultSet rs) throws SQLException {
        betaKey = rs.getString("BETAKEY");
    }

    public static BetaPlayerInfo getPlayerInfo(String uuid) {
        try (ResultSet rs = BetaKey.instance.getDbManager().getResult("SELECT * FROM betaplayer WHERE UUID='" + uuid + "'")) {
            if (rs.next()) {
                return new BetaPlayerInfo(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static int count() {
        try (ResultSet rs = BetaKey.instance.getDbManager().getResult("SELECT COUNT(*) FROM betaplayer")) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public String getBetaKey() {
        return betaKey;
    }
}
