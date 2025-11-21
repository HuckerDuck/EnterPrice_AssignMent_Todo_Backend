CREATE TABLE IF NOT EXISTS duck_task(
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       title VARCHAR(255) NOT NULL,
                       description TEXT,
                       priority VARCHAR(50) NOT NULL,
                       completed BOOLEAN NOT NULL DEFAULT false,
                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       duck_id UUID NOT NULL,


                       CONSTRAINT fk_duck_task_duck
                           FOREIGN KEY (duck_id)
                               REFERENCES ducks(id)
                               -- When a User is deleted,
                               -- all of his DuckTask aka Todos should be deleted as well
                               ON DELETE CASCADE
);

-- Putting indexes on the table
-- Making the search faster for the thing the user would probably search for
-- Making not really needed for a small project like this but
-- As it grows, it will be very useful
-- But I like to have it has a habit for future projects
CREATE INDEX idx_todos_duck_id ON duck_task (duck_id);
CREATE INDEX idx_todos_title ON duck_task (priority);
CREATE INDEX idx_todos_completed ON duck_task (completed);