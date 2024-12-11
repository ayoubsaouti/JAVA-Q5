-- Utiliser la base de données
USE player_management;

-- Table Player
CREATE TABLE IF NOT EXISTS Player (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- ID unique du joueur
    name VARCHAR(255) NOT NULL, -- Nom du joueur
    username VARCHAR(255) NOT NULL, -- Pseudonyme du joueur
    email VARCHAR(255) NOT NULL, -- Email du joueur
    level INT NOT NULL, -- Niveau du joueur
    total_points INT NOT NULL -- Points totaux du joueur
);

-- Table Friend (relation many-to-many entre joueurs)
CREATE TABLE IF NOT EXISTS Friend (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- ID unique de la relation d'amitié
    player_id BIGINT NOT NULL, -- ID du joueur
    friend_id BIGINT NOT NULL, -- ID de l'ami
    FOREIGN KEY (player_id) REFERENCES Player(id) ON DELETE CASCADE, -- Relation avec la table Player
    FOREIGN KEY (friend_id) REFERENCES Player(id) ON DELETE CASCADE, -- Relation avec la table Player (ami)
    CONSTRAINT unique_friendship UNIQUE (player_id, friend_id) -- Empêche les doublons pour une relation d'amitié
);
