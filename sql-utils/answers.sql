-- show answers for YOUR_USER
SELECT
	CONCAT(
        a1.name, ' ',
        CASE a.answer_type
            WHEN 0 THEN '>'
            WHEN 1 THEN '<'
        END,' ',
        a2.name
    ) AS value,
    a.id,
    a.answer_author
FROM answers a
JOIN assessments a1 ON a1.id = a.assessment_i_id
JOIN assessments a2 ON a2.id = a.assessment_j_id
WHERE a.user_id = { !!! YOUR_USER !!! }
ORDER BY a.id;
