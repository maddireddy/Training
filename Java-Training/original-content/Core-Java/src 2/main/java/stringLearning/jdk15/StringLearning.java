package stringLearning.jdk15;

/*
One of the most significant additions in JDK 15 was the introduction of Text Blocks,
which greatly enhanced working with multi-line string literals.
 */
public class StringLearning {
    public static void main(String[] args) {
        String html = """
                <html>
                    <body>
                        <p>Hello, Java 13!</p>
                    </body>
                </html>
                """;
        System.out.println(html);

        String name = "Siva";
        String greeting = """
                Hello,
                Dear %s,
                Welcome to our service.
                """.formatted(name);

        System.out.println(greeting);

        /*
        Text blocks can be easily concatenated with other strings or variables, maintaining readability and structure.
        Creating complex SQL queries in Java becomes more manageable and readable with the use of Text Blocks.
        Let's consider an example where we need to construct a SQL query for retrieving data from a database.
        This query involves multiple joins, conditions, and potentially complex logic:
         */

        String complexSQL = """
                SELECT 
                    u.name AS UserName,
                    p.title AS PostTitle,
                    c.name AS CategoryName
                FROM 
                    Users u
                INNER JOIN 
                    Posts p ON u.id = p.user_id
                LEFT JOIN 
                    Categories c ON p.category_id = c.id
                WHERE 
                    u.status = 'active'
                    AND p.published_date >= '2022-01-01'
                    AND (
                        c.name = 'Technology'
                        OR c.name = 'Science'
                    )
                ORDER BY 
                    p.published_date DESC
                LIMIT 10;
                """;

        System.out.println(complexSQL);
    }
}
