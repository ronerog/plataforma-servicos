DO $$
DECLARE
    v_id INTEGER;
BEGIN
    IF NOT EXISTS (SELECT 1 FROM usuario WHERE email = 'admin@plataforma.com') THEN
        INSERT INTO usuario (email, senha_hash, celular, ativo)
        VALUES (
            'admin@plataforma.com',
            '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWq',
            '82999999999',
            true
        )
        RETURNING id_usuario INTO v_id;

        INSERT INTO usuario_role (id_usuario, role)
        VALUES (v_id, 'ROLE_ADMIN');
    END IF;
END $$;