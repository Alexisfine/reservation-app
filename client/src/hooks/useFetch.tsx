import axios, { AxiosError } from "axios";
import react, {useEffect, useState} from "react";  


const useFetch = (url:string) => {
    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<any>(false);

    useEffect(() => {
        const fetchData = async () => {
            setLoading(true);
            try {
                const res = await axios.get(url);
                setData(res.data.data);
            } catch (err) {
                console.log(err);
                setError(err);
            }
            setLoading(false);
        }
        fetchData();
    },[url])

    const reFetch = async () => {
        setLoading(true);
        try {
            const res = await axios.get(url);
            setData(res.data);
        } catch (err) {
            console.log(err);
            setError(err);
        }
        setLoading(false);
    }

    return {loading, data, error, reFetch}
}

export default useFetch;

