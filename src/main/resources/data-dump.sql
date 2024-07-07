-- Users
INSERT INTO user (email, password, first_name, last_name, role, profile_picture) VALUES
                                                                                      ('superadmin1@example.com', 'password', 'Super', 'Admin1', 'SUPERADMIN', 'profile1.jpg'),
                                                                                      ('superadmin2@example.com', 'password', 'Super', 'Admin2', 'SUPERADMIN', 'profile2.jpg');

-- 10 admin users
DO $$ BEGIN
    FOR i IN 1..10 LOOP
        EXECUTE format('INSERT INTO user (email, password, first_name, last_name, role, profile_picture) VALUES (''admin%s@example.com'', ''password'', ''Admin%s'', ''User%s'', ''ADMIN'', ''profile%s.jpg'');', i, i, i, i);
END LOOP;
END $$;

-- 88 regular users
DO $$ BEGIN
    FOR i IN 1..88 LOOP
        EXECUTE format('INSERT INTO user (email, password, first_name, last_name, role, profile_picture) VALUES (''user%s@example.com'', ''password'', ''User%s'', ''Lastname%s'', ''USER'', ''profile%s.jpg'');', i, i, i, i);
END LOOP;
END $$;

-- Leagues
DO $$ BEGIN
    FOR i IN 1..10 LOOP
        EXECUTE format('INSERT INTO league (name, description, created_by) VALUES (''League %s'', ''Description for League %s'', 1);', i, i);
END LOOP;
END $$;

-- Competitions
DO $$ BEGIN
    FOR i IN 1..10 LOOP
        EXECUTE format('INSERT INTO competition (name, date, location, object_name, league_id, created_by, type, score_system) VALUES (''Competition %s'', ''2024-07-%s'', ''Location %s'', ''Object %s'', 1, 1, ''ELIMINATIONS'', ''POINTS'');', i, i, i, i);
END LOOP;

FOR i IN 11..20 LOOP
        EXECUTE format('INSERT INTO competition (name, date, location, object_name, league_id, created_by, type, score_system) VALUES (''Competition %s'', ''2024-07-%s'', ''Location %s'', ''Object %s'', NULL, 1, ''FINALS'', ''TOP_ZONE'');', i, i, i, i);
END LOOP;
END $$;

-- Results
DO $$ BEGIN
    FOR i IN 1..20 LOOP
        FOR j IN 1..40 LOOP
            EXECUTE format('INSERT INTO result (competition_id, user_id, score, top, zone, attempts, flash_attempts, total_points) VALUES (%s, %s, %s, %s, %s, %s, %s, %s);', i, j, (random() * 100)::numeric(5,2), (random() * 10)::int, (random() * 10)::int, (random() * 20)::int, (random() * 5)::int, (random() * 200)::numeric(5,2));
END LOOP;
END LOOP;
END $$;

-- BoulderResults
DO $$ BEGIN
    FOR i IN 1..20 LOOP
        FOR j IN 1..5 LOOP
            FOR k IN 1..40 LOOP
                EXECUTE format('INSERT INTO boulder_result (result_id, boulder_id, score, top, zone, attempts, flash) VALUES (%s, %s, %s, %s, %s, %s, %s);', k, j, (random() * 50)::numeric(5,2), (random() < 0.5), (random() < 0.5), (random() * 20)::int, (random() < 0.5));
END LOOP;
END LOOP;
END LOOP;
END $$;



-- Users
INSERT INTO user (email, password, first_name, last_name, role) VALUES
                                                                     ('user1@example.com', 'password1', 'John', 'Doe', 'USER'),
                                                                     ('user2@example.com', 'password2', 'Jane', 'Smith', 'USER'),
                                                                     ('admin@example.com', 'password3', 'Admin', 'User', 'ADMIN');

-- Leagues
INSERT INTO league (name, description, created_by) VALUES
                                                        ('League 1', 'Description for League 1', 1),
                                                        ('League 2', 'Description for League 2', 2);

-- Competitions
INSERT INTO competition (name, date, location, object_name, league_id, created_by, type, score_system) VALUES
                                                                                                            ('Competition 1', '2023-12-31', 'Location 1', 'Object 1', 1, 1, 'ELIMINATIONS', 'POINTS'),
                                                                                                            ('Competition 2', '2023-11-30', 'Location 2', 'Object 2', 2, 2, 'FINALS', 'TOP_ZONE');

-- Results
INSERT INTO result (competition_id, user_id, score, top, zone, attempts, flash_attempts, total_points) VALUES
                                                                                                            (1, 1, 10.0, 5, 3, 7, 2, 25.0),
                                                                                                            (2, 2, 8.0, 4, 2, 6, 1, 20.0);

-- Boulders
INSERT INTO boulder (competition_id, name) VALUES
                                                (1, 'Boulder 1'),
                                                (2, 'Boulder 2');

-- BoulderResults
INSERT INTO boulder_result (result_id, boulder_id, score, top, zone, attempts, flash) VALUES
                                                                                           (1, 1, 10.0, true, true, 3, true),
                                                                                           (2, 2, 8.0, true, false, 4, false);

-- Permissions
INSERT INTO permission (user_id, league_id, competition_id) VALUES
                                                                 (1, 1, 1),
                                                                 (2, 2, 2);
