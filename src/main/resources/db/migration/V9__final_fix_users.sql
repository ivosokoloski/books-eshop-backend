-- Додавање на колони во library_users (или users, провери како ти е името во база)
DO $$
BEGIN
    -- Проверка и додавање за created_at
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='library_users' AND column_name='created_at') THEN
ALTER TABLE library_users ADD COLUMN created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
END IF;

    -- Проверка и додавање за updated_at
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='library_users' AND column_name='updated_at') THEN
ALTER TABLE library_users ADD COLUMN updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;
END IF;

    -- Проверка и додавање за username
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='library_users' AND column_name='username') THEN
ALTER TABLE library_users ADD COLUMN username VARCHAR(255) UNIQUE;
END IF;

    -- Проверка и додавање за password
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='library_users' AND column_name='password') THEN
ALTER TABLE library_users ADD COLUMN password VARCHAR(255);
END IF;

    -- Проверка и додавање за role
    IF NOT EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name='library_users' AND column_name='role') THEN
ALTER TABLE library_users ADD COLUMN role VARCHAR(255);
END IF;
END $$;