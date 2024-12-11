-- Utiliser la base de données
USE game_management;

-- Création de la table 'game' (Partie de jeu) si elle n'existe pas
CREATE TABLE IF NOT EXISTS game (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,       -- ID unique de la partie (clé primaire)
    date DATE NOT NULL,                         -- Date de la partie (obligatoire)
    game_type VARCHAR(255) NOT NULL,            -- Type de la partie (ex : classique, rapide, etc.)
    max_score INT,                              -- Score maximum de la partie (optionnel)
    host_id BIGINT                              -- ID du joueur hôte de la partie (modifié en BIGINT pour la compatibilité)
);

-- Création de la table 'participation' (Participation des joueurs aux parties) si elle n'existe pas
CREATE TABLE IF NOT EXISTS participation (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,       -- ID unique de la participation (clé primaire)
    game_id BIGINT NOT NULL,                    -- ID de la partie associée (clé étrangère) (modifié en BIGINT)
    player_id BIGINT NOT NULL,                  -- ID du joueur participant (clé étrangère) (modifié en BIGINT)
    score INT,                                  -- Score du joueur pour cette partie
    victory BOOLEAN,                            -- Indique si le joueur a gagné ou non (true/false)
    FOREIGN KEY (game_id) REFERENCES game(id) ON DELETE CASCADE,  -- Clé étrangère vers la table 'game'
    FOREIGN KEY (player_id) REFERENCES player_management.player(id) ON DELETE CASCADE -- Clé étrangère vers la table 'player'
);
