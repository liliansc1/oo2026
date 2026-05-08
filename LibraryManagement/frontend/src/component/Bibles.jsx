// rfce

import { useEffect, useState } from "react"

function Bibles() {
    const [books, setBooks] = useState([]);

    useEffect(() => {
        async function fetchBibles() {
            try {
                const data = await fetch("https://holy-bible-api.com/bibles");
                const fetchData = await data.json();
                setBooks(fetchData.slice());
            } catch (e) {
                console.error("Unable to fetch", e);
            }
            }
            fetchBibles();
    }, []);

  return (
    <div>
        {books.map(book => 
            <div>
                <div>{book.bible_id}</div>
                <div>{book.language}</div>
                <div>{book.version}</div>
                <br />
            </div>)}
    </div>
  )
}

export default Bibles