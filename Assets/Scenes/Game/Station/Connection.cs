using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public enum TransportationType
{
    TAXI,
    BUS,
    METRO,
    FERRY

       
}

public class Connection : MonoBehaviour
{
    public TransportationType transportationType;
    public Station targetStation;

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
