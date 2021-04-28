using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public enum TransportationType
{
    Taxi,
    Bus,
    Subway,
    Ferry
}

public class Connection : MonoBehaviour
{
    public TransportationType transportationType;
    public int targetStationId;

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
